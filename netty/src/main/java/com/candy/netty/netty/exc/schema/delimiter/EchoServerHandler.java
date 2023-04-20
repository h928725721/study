package com.candy.netty.netty.exc.schema.delimiter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerInvoker;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.StandardCharsets;

@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelHandlerAdapter {
    int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("This is " + ++counter + " times receive client : [" + body + "]");
        /*
         * 由于DelimiterBasedFrameDecoder过滤掉了分隔符，所以返回时需要加上分隔符
         */
        body += "$_";
        ByteBuf echo = Unpooled.copiedBuffer(body.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(echo);
    }

    /**
     * 异常关闭链路
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
