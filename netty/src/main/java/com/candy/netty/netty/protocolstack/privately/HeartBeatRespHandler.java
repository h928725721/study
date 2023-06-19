package com.candy.netty.netty.protocolstack.privately;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 心跳响应处理器
 */
public class HeartBeatRespHandler extends ChannelHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyProtocolMessage nettyProtocolMessage = (NettyProtocolMessage) msg;
        Header header = nettyProtocolMessage.getHeader();
        if (header != null && MessageType.HEARTBEAT_REQ.value() == header.getType()) {
            System.out.println("Receive client heart beat message : ---->" + nettyProtocolMessage);
            NettyProtocolMessage heartBeat = buildHeartBeat();
            System.out.println("Send heart beat response message to client : --->" + heartBeat);
            ctx.writeAndFlush(heartBeat);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyProtocolMessage buildHeartBeat() {
        Header header = Header.builder().type(MessageType.HEARTBEAT_RESP.value()).build();
        return NettyProtocolMessage.builder().header(header).build();
    }
}
