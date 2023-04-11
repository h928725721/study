package com.candu.concurrency.part1.safe;

import lombok.Data;

import java.util.Random;

@Data
public class Account {
    private static final int THRESHOLD = 500;
    private int balance;
    private Random random;
    public synchronized void transferCredits (Account from,Account to,int amount) {
        from.setBalance(from.getBalance() - amount);
        if (random.nextInt(1000) > THRESHOLD) {
            Thread.yield();
        }
        to.setBalance(to.getBalance() + amount);
    }

}
