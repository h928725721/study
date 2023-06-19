package com.candy.netty.netty.protocolstack.privately;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NettyClient {

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    private final NioEventLoopGroup group = new NioEventLoopGroup();

    public static void main(String[] args) throws Exception {
        new NettyClient().connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
    }

    public void connect(int port, String host) throws Exception {
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    /**
                                     * 为了防止单条消息过大导致的内存溢出或者畸形码流导致解码错位引起内存分配失败，对单条消息最大长度进行上限限制
                                     * 4，4 代表长度字段的位置以及长度
                                     * -8 代表矫正数据包的长度，因为在代码中 ：frameLength += lengthAdjustment + lengthFieldEndOffset; 通过frameLength进行获取包的长度
                                     * 如果不指定 lengthAdjustment，默认为0，而：lengthFieldEndOffset = lengthFieldOffset + lengthFieldLength = 8
                                     * 例如发送包长度为26，不指定 lengthAdjustment
                                     * frameLength += lengthAdjustment + lengthFieldEndOffset = 34 进行对比时发现包长度 26 < 34 就会返回 null
                                     * 这时我们就需要指定 lengthAdjustment = -8，将多加上的长度字段给减去掉
                                     */
                                    .addLast(new NettyMessageDecoder(1024 * 1024, 4, 4, -8, 0))
                                    .addLast("MessageEncoder", new NettyMessageEncoder())
                                    //超时处理器，在50秒自动断开连接
                                    .addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                            ch.pipeline()
                                    .addLast("LoginAuthReqHandler", new LoginAuthReqHandler());
                            ch.pipeline()
                                    .addLast("HeartBeatReqHandler", new HeartBeatReqHandler());
                        }
                    });
            ChannelFuture future = b.connect(new InetSocketAddress(host, port),
                    //进行连接时绑定本地的端口和ip，用于进行客户端去重
                    new InetSocketAddress(NettyConstant.LOCALIP, NettyConstant.LOCAL_PORT)).sync();
            future.channel().closeFuture().sync();
        } finally {
            //所有资源释放完成之后，清空资源，再次放弃重连操作
            System.out.println("执行服务重连");
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    //发起连接
                    connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

    }

}
