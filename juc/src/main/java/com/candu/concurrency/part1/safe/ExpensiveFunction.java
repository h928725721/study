package com.candu.concurrency.part1.safe;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class ExpensiveFunction implements Computable<String , BigInteger>{
    @Override
    public BigInteger computable(String arg) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return new BigInteger(arg);
    }
}
