package com.candy.netty.netty.codingframe.messagepack;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserInfo implements Serializable {
    private String userName;
    private int userId;
    private int age;
    public UserInfo buildUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserInfo buildUserId(int userId) {
        this.userId = userId;
        return this;
    }
}
