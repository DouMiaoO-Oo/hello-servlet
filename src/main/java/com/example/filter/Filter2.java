package com.example.filter;

import javax.servlet.*;
import java.io.IOException;

public class Filter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(Thread.currentThread().getName() + ": Filter2 init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println(Thread.currentThread().getName() + ": Filter2 doFilter");
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println(Thread.currentThread().getName() + ": Filter2 destroy");
    }
}
