package com.candu.concurrency.part1.safe;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

@NotThreadSafe
public class NumberRange {
    // 不变性条件: lower <= upper
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        if (i > upper.get()) {
            throw new IllegalArgumentException("can not set lower to " + i + "> upper");
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        if (i < lower.get()) {
            throw new IllegalArgumentException("can not set upper to " + i + "< lower");
        }
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return (i >= lower.get() && i <= upper.get());
    }
}
