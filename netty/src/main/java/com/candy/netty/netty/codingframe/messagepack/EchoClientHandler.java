package com.candy.netty.netty.codingframe.messagepack;

import org.msgpack.MessagePack;
import org.msgpack.type.Value;
import org.msgpack.type.ValueFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    private final User user;

    public EchoClientHandler() {
        user = new User("John Doe", 25);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        MessagePack msgpack = new MessagePack();
        byte[] bytes = null;
        try {
            bytes = msgpack.write(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);
        ctx.writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        MessagePack msgpack = new MessagePack();
        try {
            Value value = msgpack.read(bytes);
            User user = new User(value.asMapValue().get(ValueFactory.createRawValue("name")).asRawValue().getString(),
                    value.asMapValue().get(ValueFactory.createRawValue("age")).asIntegerValue().getInt());
            System.out.println("Server received: " + user);
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
