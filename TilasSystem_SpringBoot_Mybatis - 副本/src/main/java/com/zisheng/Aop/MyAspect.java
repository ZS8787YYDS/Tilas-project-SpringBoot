package com.zisheng.Aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Order(2)
@Slf4j
@Component//将该类的对象交给IOC容器进行管理，成为IOC容器的bean对象
//@Aspect//表明该类为AOP类
public class MyAspect {
    @Before("com.zisheng.Aop.TimeAspect.exp()")
    public void Before()
    {
        log.info("22222222222222222可以定义一个方法，采用public进行修饰，通过@Pointcut注解将切入点表达式提取出来，就可以通过方法调用的方式获取到切入点表达式");
    }
}
