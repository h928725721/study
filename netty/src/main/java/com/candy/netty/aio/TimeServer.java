package com.candy.netty.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class TimeServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(8080));
        System.out.println("TimeServer started on port 8080...");

        serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {

            @Override
            public void completed(AsynchronousSocketChannel clientChannel, Void attachment) {
                serverChannel.accept(null, this); // 接收下一个客户端连接

                ByteBuffer buffer = ByteBuffer.allocate(32);
                clientChannel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                        attachment.flip();
                        byte[] bytes = new byte[attachment.remaining()];
                        attachment.get(bytes);
                        String request = new String(bytes);
                        System.out.println("Request: " + request);

                        if (request.trim().equals("time")) {
                            String response = new Date().toString();
                            ByteBuffer writeBuffer = ByteBuffer.wrap(response.getBytes());
                            clientChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                                @Override
                                public void completed(Integer result, ByteBuffer attachment) {
                                    if (writeBuffer.hasRemaining()) {
                                        clientChannel.write(writeBuffer, writeBuffer, this);
                                    } else {
                                        try {
                                            clientChannel.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                @Override
                                public void failed(Throwable exc, ByteBuffer attachment) {
                                    exc.printStackTrace();
                                }
                            });
                        }
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        exc.printStackTrace();
                    }
                });

            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                exc.printStackTrace();
            }
        });

        Thread.currentThread().join();
    }

}
