package com.candy.netty.netty.codingframe.messagepack;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

public class EchoServerHandler extends ChannelHandlerAdapter {
    private final int sendNumber;

    public EchoServerHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            System.out.println("Server received: " + msg);
            List<User> users = users();
            ctx.write(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<User> users() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < sendNumber; i++) {
            User user = new User("James Kt" + i, i + 1);
            list.add(user);
        }
        return list;
    }

    /**
     * 当 Channel 上的一个读操作完成时会自动调用该方法，可以用来处理读取完成后的操作，比如刷新缓冲区，向客户端发送消息等。
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
