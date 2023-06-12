package com.candy.netty.netty.protocolstack;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * message类
 */
@Getter
@Setter
@ToString
public final class NettyMessage {
    private Header header;
    private Object body;

}
