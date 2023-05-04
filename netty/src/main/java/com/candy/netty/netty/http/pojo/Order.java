package com.candy.netty.netty.http.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private long orderNumber;
    private Customer customer;

    private Address billTo;

    private Shipping  shipping;

    private Address shipTo;

    private Float total;
}
