package com.candy.netty.bio.fakeasync;

import com.candy.netty.bio.sync.timeserver.TimeServerHandler;
import lombok.SneakyThrows;

import java.net.ServerSocket;
import java.net.Socket;

public class FakeAsyncTimeServer {
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
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 1000);
            while (true) {
                //如果没有客户端接入，主线程阻塞在accept操作上
                socket = server.accept();
                //调用线程池，避免每个任务创建一个线程
                singleExecutor.execute(new TimeServerHandler(socket));
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
