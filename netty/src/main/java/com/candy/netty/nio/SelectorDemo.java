package com.candy.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorDemo {
    public static void main(String[] args) throws IOException {
        // 创建 Selector 对象
        Selector selector = Selector.open();
        // 创建两个 ServerSocketChannel
        ServerSocketChannel channel1 = ServerSocketChannel.open();
        channel1.socket().bind(new InetSocketAddress("localhost", 8888));
        ServerSocketChannel channel2 = ServerSocketChannel.open();
        channel2.socket().bind(new InetSocketAddress("localhost", 9999));
        // 设置为非阻塞模式
        channel1.configureBlocking(false);
        channel2.configureBlocking(false);
        // 注册到 Selector 上，并关注 ACCEPT 事件
        channel1.register(selector, SelectionKey.OP_ACCEPT);
        channel2.register(selector, SelectionKey.OP_ACCEPT);
        // 不断轮询 Selector，处理发生的事件
        while (true) {
            int count = selector.select();
            if (count > 0) {
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        handleAccept(key);
                    }
                    iterator.remove();
                }
            }
        }
    }

    private static void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        channel.accept().configureBlocking(false).register(key.selector(), SelectionKey.OP_READ);
        System.out.println("接收到连接请求，连接的地址：" + channel.socket().getLocalSocketAddress());
    }
}
