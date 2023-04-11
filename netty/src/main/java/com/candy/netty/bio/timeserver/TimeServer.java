package com.candy.netty.bio.timeserver;

import lombok.SneakyThrows;

import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
    @SneakyThrows
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                //do something
            }
        }

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port : " + port);
            Socket socket = null;
            while (true) {
                //如果没有客户端接入，主线程阻塞在accpet操作上
                socket = server.accept();
                //BIO的问题，每有一个客户端请求接入时，服务端必须创建一个新的线程处理新接入的客户端链路，一个线程只能处理一个客户端连接
                new Thread(new TimeServerHandler(socket)).start();
            }
        } finally {
            if (server != null) {
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }

}
