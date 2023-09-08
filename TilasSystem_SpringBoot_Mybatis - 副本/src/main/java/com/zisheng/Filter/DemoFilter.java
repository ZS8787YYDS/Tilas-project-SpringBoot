package com.zisheng.Filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@Slf4j
//@WebFilter(urlPatterns = "/*")//代表拦截到所有请求
public class DemoFilter implements Filter {
    //创建日志记录对象
    private static final Logger log = LoggerFactory.getLogger(DemoFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException//初始化方法，只会执行一次，在服务器启动之后，创建Filter对象时自动执行
    {
//        System.out.println("init() 初始化方法执行了~");
        log.info("init() 初始化方法执行了！");
    }

    @Override
    //核心方法，每次拦截到请求都会执行，会执行多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("拦截到请求了");
        log.info("Demo拦截到请求了~");
        //放行请求，使其能够访问到对应的接口
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("Demo拦截放行了~~");
    }

    @Override
    public void destroy()//销毁方法，在服务器关闭时会自动执行，也是只会执行一次
    {
        log.info("destory() 方法执行了~~~");
    }
}
