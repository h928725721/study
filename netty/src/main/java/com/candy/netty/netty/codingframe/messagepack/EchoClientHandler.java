package com.candy.netty.netty.codingframe.messagepack;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import lombok.SneakyThrows;

public class EchoClientHandler extends ChannelHandlerAdapter {

    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @SneakyThrows
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        for (int i = 0; i < sendNumber; i++) {
            User user = new User("John Doe" + i, i);
            ctx.write(user);
        }
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            System.out.println("Server received: " + msg);
            ctx.write(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
