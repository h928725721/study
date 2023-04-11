package com.candu.concurrency.part1.safe;

import java.util.concurrent.ExecutionException;

public interface Computable<A, V> {
    V computable(A arg) throws InterruptedException, ExecutionException;
}
