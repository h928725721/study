package com.candu.concurrency.part1.safe;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ThreadSafe
public class PrimeGenerator implements Runnable{
    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList<>();
    private volatile boolean cancelled;
    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }
    public void cancel() {
        cancelled = true;
    }
    public synchronized List<BigInteger> get() {
        return new ArrayList<>(primes);
    }



    List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        PrimeGenerator primeGenerator = new PrimeGenerator();
        new Thread(primeGenerator).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } finally {
            primeGenerator.cancel();
        }
        return primeGenerator.get();
    }
}
