package com.candu.concurrency.part1.safe;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.*;
import java.math.BigInteger;

@ThreadSafe
public class CachedFactorizer implements Servlet {
    @GuardedBy("this") private BigInteger lastNumber;
    @GuardedBy("this") private BigInteger[] lastFactors;
    @GuardedBy("this") private long hits;
    @GuardedBy("this") private long cacheHits;


    public synchronized long getHits() {return this.hits;}
    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) {
        BigInteger i = BigInteger.valueOf(111);
        BigInteger[] factors = null;
        synchronized (this) {
            ++hits;
            if (i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        if (factors == null) {
            factors = factors(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }

        //doSomething

    }

    private BigInteger[] factors(BigInteger i) {
        return new BigInteger[0];
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }
}
