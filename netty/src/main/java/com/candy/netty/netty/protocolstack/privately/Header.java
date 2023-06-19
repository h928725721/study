package com.candy.netty.netty.protocolstack.privately;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息头类
 */
@Data
@ToString
@Builder
public final class Header {
    private int crcCode = 0xabef0101;

    /**
     * 消息长度
     */
    private int length ;

    /**
     * 会话ID
     */
    private long sessionID;

    /**
     * 消息类型
     */
    private byte type;

    /**
     * 消息优先级
     */
    private byte priority;

    /**
     * 附件
     */
    private Map<String,Object> attachment = new HashMap<>();




}
