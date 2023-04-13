package com.candy.netty.netty.exc.problem;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * netty时间服务器服务端
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    private int counter;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        //buf.readableBytes()获取缓冲区的可读的字节数，根据可读的字节数创建byte数组
        byte[] req = new byte[buf.readableBytes()];
        //将缓冲区中的字节数组复制到新建的byte数组中
        buf.readBytes(req);
        String body = new String(req, StandardCharsets.UTF_8).substring(0,req.length-System.getProperty("line.separator").length());
        System.out.println("The time server receive order : " + body + "; the counter is : " + ++counter);


        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
