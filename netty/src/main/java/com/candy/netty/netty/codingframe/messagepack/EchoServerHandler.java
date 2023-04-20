package com.candy.netty.netty.codingframe.messagepack;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelHandlerAdapter {
    private final int sendNumber;

    public EchoServerHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client receive the msgpack message : " + msg);

        UserInfo[] infos = userInfo();
        for (UserInfo infoE : infos) {
            ctx.write(infoE);
        }
        ctx.flush();
    }
    private UserInfo[] userInfo() {
        UserInfo[] userInfos = new UserInfo[sendNumber];
        UserInfo userInfo = null;
        for (int i = 0; i < sendNumber; i++) {
            userInfo = new UserInfo();
            userInfo.setAge(i);
            userInfo.setUserName("ABCDEFG ---------> " + i);
            userInfos[i] = userInfo;
        }
        return userInfos;
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
