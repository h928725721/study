package com.candu.concurrency.part1.safe;

import java.util.Map;
import java.util.concurrent.*;

public class Memoizer3<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;
    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V computable(final A arg) throws InterruptedException, ExecutionException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = () -> c.computable(arg);
            FutureTask<V> ft = new FutureTask<>(eval);
            f = ft;
            cache.put(arg, ft);
            //这里将调用c.compute
            ft.run();
        }
        return f.get();
    }
}
