package org.nelect.timestamper.internal.agent;

import java.text.ParseException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.*;
import io.netty.handler.codec.string.LineEncoder;
import io.netty.handler.codec.string.LineSeparator;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import org.apache.commons.codec.binary.Hex;
import org.nelect.timestamper.TimestamperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by Michael on 2016/6/7.
 */
public class TimestampAgentImpl implements TimestampAgent {

    private static final AttributeKey<byte[]> DIGEST_ATTR_KEY = AttributeKey.valueOf("digest");
    private static final AttributeKey<ConfirmationListener> LISTENER_ATTR_KEY = AttributeKey.valueOf("confirmationListener");

    private EventLoopGroup group;
    private Bootstrap bootstrap;
    private final Object bootstrapMonitor = new Object();

    private String host;
    private int port;
    private int minConfirmations;

    public TimestampAgentImpl(Properties config) {
        host = config.getProperty("timestamp.server.host", "localhost");
        port = Integer.parseInt(config.getProperty("timestamp.server.port", "9000"));
        minConfirmations = Integer.parseInt(config.getProperty("timestamp.minConfirmations", "3"));

        group = new NioEventLoopGroup();
        try {
            bootstrap = new Bootstrap();
            bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new TimestampClientInitializer());
        } catch (RuntimeException re) {
            group.shutdownGracefully();
            throw re;
        }
    }

    @Override
    public String timestamp(byte[] digest, ConfirmationListener listener) {

        ChannelFuture connectFuture;

        synchronized (bootstrapMonitor) {
            bootstrap.attr(DIGEST_ATTR_KEY, digest);
            bootstrap.attr(LISTENER_ATTR_KEY, listener);
            connectFuture = bootstrap.connect(host, port);
        }

        connectFuture.syncUninterruptibly();

        TimestampClientHandler handler = (TimestampClientHandler) connectFuture.channel().pipeline().last();

        return getUninterruptibly(handler.getTransactionIdFuture());
    }

    private String getUninterruptibly(Future<String> future) {
        boolean interrupted = Thread.interrupted();
        try {
            while (true) {
                try {
                    return future.get();
                } catch (InterruptedException ignore) {
                    interrupted = true;
                } catch (ExecutionException e) {
                    throw (TimestamperException) e.getCause();
                }
            }
        } finally {
            if (interrupted) Thread.currentThread().interrupt();
        }
    }

    @Override
    public String timestamp(byte[] digest) {
        return timestamp(digest, null);
    }

    public void close() {
        group.shutdownGracefully();
    }

    private class Request {
        byte[] digest;
        int minConfirmations;
    }

    private class Response {
        String transactionId;
        int nConfirmations;
    }

    private class TimestampClientHandler extends SimpleChannelInboundHandler<Response> {

        private final Logger logger = LoggerFactory.getLogger(this.getClass());

        private Request request;
        private ConfirmationListener listener;

        private final BlockingQueue<String> transactionIdResult = new LinkedBlockingQueue<>(1);
        private Future<String> transactionIdFuture = new TransactionIdFuture();

        /*
         * 0 - 初始状态，客户端尚未注册信道
         * 1 - 已经注册信道，已经绑定请求数据和监听器
         * 2 - 连接状态，请求已经发送
         * 3 - 收到服务器第一条响应
         * 4 - 与服务器所有会话结束，主动关闭信道
         * [0]-1 - 信道注册失败（客户端）
         * [1]-2 - 请求发送失败（客户端Exception）
         * [2]-3 - 请求响应失败/客户端读取失败（服务器Inactive/客户端Exception）
         * [3]-4 - 服务器异常/客户端读取失败（服务器Inactive/客户端Exception）
         */
        private int state = 0;

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            request = new Request();
            request.digest = ctx.channel().attr(DIGEST_ATTR_KEY).get();
            request.minConfirmations = minConfirmations;

            listener = ctx.channel().attr(LISTENER_ATTR_KEY).get();
            state = 1;
            super.channelRegistered(ctx);
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(request);
            state = 2;
            super.channelActive(ctx);
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Response msg) throws Exception {

            state = 3;
            transactionIdResult.offer(msg.transactionId);

            if (listener != null) {
                if (msg.nConfirmations > 0) {
                    if (msg.nConfirmations <= minConfirmations) {
                        try {
                            listener.onConfirm(msg.transactionId, msg.nConfirmations,
                                msg.nConfirmations == minConfirmations);
                        } catch (Exception e) {
                            logger.warn("交易进度事件回调出错", e);
                        }
                    }
                    if (msg.nConfirmations >= minConfirmations) {
                        state = 4;
                        ctx.close();
                    }
                }
            } else {
                state = 4;
                ctx.close();
            }
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            switch (state) {
                case 3:
                    if (listener != null) {
                        try {
                            listener.onError(new TimestamperException("服务器异常关闭"));
                        } catch (Exception e) {
                            logger.warn("交易进度异常回调出错", e);
                        }
                    }
                    break;
                case 2:
                case 1:
                case 0:
                    transactionIdResult.offer("");
            }

            super.channelInactive(ctx);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            try {
                logger.warn("Exception caught in channel", cause);
                switch (state) {

                    case 3:
                        if (listener != null) {
                            try {
                                listener.onError(cause);
                            } catch (Exception e) {
                                logger.warn("交易进度异常回调出错", e);
                            }
                        }
                        break;
                    case 2:
                    case 1:
                    case 0:
                        transactionIdResult.offer("");
                }
            } finally {
                logger.warn("Auto closing the context");
                ctx.close();
            }
        }

        Future<String> getTransactionIdFuture() {
            return transactionIdFuture;
        }

        private class TransactionIdFuture implements Future<String> {

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return !transactionIdResult.isEmpty();
            }

            @Override
            public String get() throws InterruptedException, ExecutionException {
                String result = transactionIdResult.take();
                if (isBlank(result))
                    throw new ExecutionException(new TimestamperException("区块链存证请求失败"));
                else
                    return result;
            }

            @Override
            public String get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                throw new UnsupportedOperationException("暂不支持");
            }
        }
    }

    private class TimestampClientInitializer extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline()
                .addLast(new LineEncoder(LineSeparator.UNIX, CharsetUtil.UTF_8))
                .addLast(new LineBasedFrameDecoder(255))

                .addLast(new MessageToMessageEncoder<Request>() {

                    @Override
                    protected void encode(ChannelHandlerContext ctx, Request msg, List<Object> out) throws Exception {
                        String str = Hex.encodeHexString(msg.digest) + "," + msg.minConfirmations;
                        out.add(str);
                    }
                })
                .addLast(new ByteToMessageDecoder() {

                    @Override
                    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
                        in.markReaderIndex();
                        String str = in.readCharSequence(in.readableBytes(), CharsetUtil.UTF_8).toString();
                        String[] elements = str.split(",", 2);
                        if (elements.length < 2) {
                            in.resetReaderIndex();
                            throw new DecoderException("响应报文格式错误");
                        }

                        Response response = new Response();
                        response.transactionId = elements[0];
                        try {
                            response.nConfirmations = Integer.parseInt(elements[1]);
                        } catch (NumberFormatException nfe) {
                            in.resetReaderIndex();
                            throw new DecoderException("响应报文格式错误", nfe);
                        }
                        out.add(response);
                    }
                })

                .addLast(new TimestampClientHandler());
        }
    }
}
