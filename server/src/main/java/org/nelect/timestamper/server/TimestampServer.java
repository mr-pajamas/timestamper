package org.nelect.timestamper.server;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.*;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.string.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;
import org.apache.commons.codec.*;
import org.apache.commons.codec.binary.Hex;

import static org.apache.commons.lang3.StringUtils.trimToNull;

/**
 * Created by Michael on 2016/6/5.
 */
public final class TimestampServer {

    private int port;
    private final Properties config;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private Channel serverChannel;

    private TestNetTimestamper timestamper;

    public TimestampServer(int port, Properties config) {
        this.port = port;
        this.config = config;
    }

    public void start() {
        timestamper = new TestNetTimestamper(new File(config.getProperty("wallet.location")),
            config.getProperty("wallet.prefix"));

        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new TimestampServerInitializer())
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

            serverChannel = bootstrap.bind(port).syncUninterruptibly().channel();
            serverChannel.closeFuture().sync();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            shutdown();
        }
    }

    public void shutdown() {
        serverChannel.close().syncUninterruptibly();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();

        timestamper.close();
    }

    private class TimestampServerInitializer extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();

            pipeline.addLast(new LineBasedFrameDecoder(255));
            pipeline.addLast(new LineEncoder(LineSeparator.UNIX, CharsetUtil.UTF_8));

            pipeline.addLast(new ByteToMessageDecoder() {

                @Override
                protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
                    in.markReaderIndex();
                    String str = in.readCharSequence(in.readableBytes(), CharsetUtil.UTF_8).toString();
                    String[] elements = str.split(",", 2);
                    if (elements.length < 2) {
                        in.resetReaderIndex();
                        throw new DecoderException("请求报文格式错误");
                    }

                    TimestampRequest request = new TimestampRequest();
                    try {
                        request.setData(Hex.decodeHex(elements[0].toCharArray()));
                        request.setConfirmationThreshold(Integer.parseInt(elements[1]));
                    } catch (org.apache.commons.codec.DecoderException | NumberFormatException e) {
                        in.resetReaderIndex();
                        throw new DecoderException("请求报文格式错误", e);
                    }
                    out.add(request);
                }
            });
            pipeline.addLast(new MessageToMessageEncoder<TimestampResponse>() {

                @Override
                protected void encode(ChannelHandlerContext ctx, TimestampResponse msg, List<Object> out) throws Exception {

                    String str = msg.getTransactionId() + "," + msg.getnConfirmations();
                    out.add(str);
                }
            });

            pipeline.addLast(new TimestampServerHandler(timestamper));
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TimestampServer.class.getResourceAsStream("/timestamper.server.properties")) {
            config.load(in);
        }

        int port = 9000;
        String portString;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else if ((portString = trimToNull(config.getProperty("server.port"))) != null) {
            port = Integer.parseInt(portString);
        }

        final TimestampServer server = new TimestampServer(port, config);

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                server.shutdown();
            }
        });

        server.start();
    }
}
