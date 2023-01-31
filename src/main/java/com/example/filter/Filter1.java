package com.example.filter;

import javax.servlet.*;
import java.io.IOException;

public class Filter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(Thread.currentThread().getName() + ": Filter1 init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println(Thread.currentThread().getName() + ": Filter1 doFilter");  // 在同一个线程中执行各种 Filters
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println(Thread.currentThread().getName() + ": Filter1 destroy");
    }
}
