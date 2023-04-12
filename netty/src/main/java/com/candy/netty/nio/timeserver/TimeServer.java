package com.candy.netty.nio.timeserver;

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
        new Thread(new MultiplexerTimeServer(port),"NIO-MultiplexerTimeServer-001").start();
    }

}
