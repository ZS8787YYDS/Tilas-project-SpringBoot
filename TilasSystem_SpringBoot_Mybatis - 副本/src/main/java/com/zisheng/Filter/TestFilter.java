package com.zisheng.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
//@WebFilter(urlPatterns = "/*")
public class TestFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(TestFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Test拦截到请求了~");
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("Test拦截放行了~~");
    }
}
