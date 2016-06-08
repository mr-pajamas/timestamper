package org.nelect.timestamper.server;

import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Michael on 2016/6/5.
 */
public class TimestampServerHandler extends SimpleChannelInboundHandler<TimestampRequest> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Timestamper timestamper;

    public TimestampServerHandler(Timestamper timestamper) {
        this.timestamper = timestamper;
    }

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final TimestampRequest msg) throws Exception {
        String txId = timestamper.timestamp(msg, new Timestamper.ConfirmationHandler() {

            @Override
            public void onNConfirmationsChange(String txId, int nConfirmations) {
                if (ctx.channel().isActive()) {
                    TimestampResponse response = new TimestampResponse();
                    response.setTransactionId(txId);
                    response.setnConfirmations(nConfirmations);
                    ChannelFuture channelFuture = ctx.writeAndFlush(response);

                    if (nConfirmations >= msg.getConfirmationThreshold()) {
                        channelFuture.addListener(ChannelFutureListener.CLOSE);
                    }
                }
            }
        });
        TimestampResponse response = new TimestampResponse();
        response.setTransactionId(txId);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("Exception caught and auto closing the context", cause);
        ctx.close();
    }
}
