package com.candy.netty.netty.protocolstack.privately;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class HeartBeatReqHandler extends ChannelHandlerAdapter {

    /**
     * 心跳检测定时任务
     */
    private volatile ScheduledFuture<?> heartbeat;

    /**
     * Channel read
     *
     * @param ctx ctx
     * @param msg msg
     * @throws Exception exception
     * @since 1.0
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyProtocolMessage nettyProtocolMessage = (NettyProtocolMessage) msg;
        Header header = nettyProtocolMessage.getHeader();
        //客户端登录成功后，返回了响应信息后再执行心跳
        if (header != null && MessageType.LOGIN_RESP.value() == header.getType()) {
            //创建一个定时任务，初始0秒开始执行，间隔5000毫秒执行一次
            if (this.heartbeat == null) {
                this.heartbeat = ctx.executor().scheduleAtFixedRate(new HeartBeatTask(ctx), 0, 10, TimeUnit.SECONDS);
            }
        } else if(header != null && MessageType.HEARTBEAT_RESP.value() == header.getType()) {
            System.out.println("Client receive server heart message : -----> " + nettyProtocolMessage);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    /**
     * Exception caught
     *
     * @param ctx   ctx
     * @param cause cause
     * @throws Exception exception
     * @since 1.0
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (heartbeat != null) {
            heartbeat.cancel(true);
            heartbeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }

    /**
     * 定时任务，定时发送心跳请求到客户端
     */
    private static class HeartBeatTask implements Runnable {

        /** Ctx */
        private final ChannelHandlerContext ctx;

        /**
         * Heart beat task
         *
         * @param handlerContext handler context
         * @since 1.0
         */
        public HeartBeatTask(ChannelHandlerContext handlerContext) {
            this.ctx = handlerContext;
        }

        /**
         * Run
         *
         * @since 1.0
         */
        @Override
        public void run() {
            NettyProtocolMessage message = buildHeartbeat();
            System.out.println("Client send heart beat message to server : -----> " + message);
            this.ctx.writeAndFlush(message);
        }

        /**
         * Build heartbeat
         *
         * @return the netty protocol message
         * @since 1.0
         */
        private NettyProtocolMessage buildHeartbeat() {
            Header header = Header.builder().type(MessageType.HEARTBEAT_REQ.value()).build();
            return NettyProtocolMessage.builder().header(header).build();
        }
    }
}
