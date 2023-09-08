package com.zisheng.Config;

import com.zisheng.Interceptor.DemoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置、注册拦截器
 */
@Configuration//表明该类为配置类
public class InterceptorConfig implements WebMvcConfigurer {
    //采用自动注入的形式提供该类的bean对象，赋值给该变量
    @Autowired
    private DemoInterceptor demoInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器并且设置拦截路径为所有请求路径
        registry.addInterceptor(demoInterceptor).addPathPatterns("/**");
    }
}
