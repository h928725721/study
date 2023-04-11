package com.candu.concurrency.part1.safe;

import java.util.Map;
import java.util.concurrent.*;

public class Memoizer4<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;
    public Memoizer4(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V computable(final A arg) throws InterruptedException, ExecutionException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = () -> c.computable(arg);
                FutureTask<V> ft = new FutureTask<>(eval);
                f = cache.putIfAbsent(arg,ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            return f.get();
        }

    }
}
