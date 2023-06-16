package com.candy.netty.netty.protocolstack.privately;

public enum MessageType {

    LOGIN_REQ(3),
    LOGIN_RESP(4),

    HEARTBEAT_REQ(5),
    HEARTBEAT_RESP(6)
    ;


    final int value;

    MessageType(int i) {
        this.value = i;
    }


    public byte value() {
        return (byte) this.value;
    }
}
