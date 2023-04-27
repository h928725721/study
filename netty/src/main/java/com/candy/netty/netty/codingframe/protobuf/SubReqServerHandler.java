package com.candy.netty.netty.codingframe.protobuf;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
        if ("Lilinfeng" .equals(req.getUserName())) {
            System.out.println("Service accept client subscribe req : ["+ req + "]");
            ctx.writeAndFlush(resp(req.getSubReqID()));
        }

    }

    private SubscribeRespProto.SubscribeResp resp ( int subReqId) {
        SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
        builder.setSubReqId(subReqId);
        builder.setRespCode(0);
        builder.setDesc("Netty book order succeed, 3 days later, sent to the designated address");
        return builder.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
