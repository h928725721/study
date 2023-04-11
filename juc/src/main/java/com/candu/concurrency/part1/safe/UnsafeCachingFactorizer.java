package com.candu.concurrency.part1.safe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

public class UnsafeCachingFactorizer implements Servlet {
    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
    private final AtomicReference<BigInteger> lastFactors = new AtomicReference<>();

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = BigInteger.valueOf(111111);
        if (i.equals(lastNumber.get())) {
            BigInteger f = lastFactors.get();
            //do something f
        }else {
            BigInteger factors = factor(i);
            lastNumber.set(i);
            lastFactors.set(factors);
            //do something
        }
    }

    private BigInteger factor(BigInteger i) {
        return null;
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
