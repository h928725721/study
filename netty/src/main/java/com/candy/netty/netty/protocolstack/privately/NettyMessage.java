package com.candy.netty.netty.protocolstack.privately;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public final class NettyMessage {
    private Header header;

    private Object body;
}
