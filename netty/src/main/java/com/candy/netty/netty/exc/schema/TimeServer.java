package com.candy.netty.netty.exc.schema;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import lombok.SneakyThrows;

public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                //采用默认值
            }
        }
        new TimeServer().bind(port);
    }

    @SneakyThrows
    public void bind(int port) {
        //配置服务端的NIO线程组,包含一组NIO，一个用于服务端接受客户端连接，一个用于SocketChannel的网络读写
        NioEventLoopGroup bassGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //netty用于启动NIO的辅助启动类
            ServerBootstrap b = new ServerBootstrap();
            b.group(bassGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());
            //绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();
            //等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            //优雅退出
            bassGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private static class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        /**
         * LineBasedFrameDecoder：依次遍历ByteBuf中的可读字节，半段是否有“\n”或者"\r\n"，如果有就以此位置为结束位置，从可读索引到结束位置区间的字节就组成了一行。
         *                        以换行符为结束标志的编码器，支持携带结束符或不懈怠结束符两种解码方式，支持配置单行最大长度
         *                        连续读取到最大长度仍然没有发现换行符，就会抛出异常并忽略异常码流
         *
         * StringDecoder： 将接收到的对象转换成字符串，然后调用后面的Handler.
         *
         *
         * LineBasedFrameDecoder + StringDecoder 组合就是按行切换的文本解码器
         */
        @Override
        protected void initChannel(SocketChannel arg0) throws Exception {
            arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));
            arg0.pipeline().addLast(new StringDecoder());
            arg0.pipeline().addLast(new TimeServerHandler());

        }
    }

}
