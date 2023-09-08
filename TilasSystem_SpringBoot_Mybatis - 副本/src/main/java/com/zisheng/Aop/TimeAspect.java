package com.zisheng.Aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Order(1)
@Component//将该类的对象交给IOC容器进行管理，成为IOC容器的bean对象
//@Aspect//表明该类为AOP类，不是普通的java类
public class TimeAspect {
    //创建日志记录对象，用于记录日志
    private static final Logger log = LoggerFactory.getLogger(TimeAspect.class);
    //定义一个方法，通过Pointcut注解将切入点表达式提取出来
    @Pointcut("execution(* com.zisheng.Service.*.*(..))")
    public void exp(){}
    // around注解标注的方法表示该方法的部分逻辑在目标方法执行之前执行，
    // 部分逻辑在目标方法执行之后执行
    @Around("exp()")//切入点表达式
    public Object recordTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Around注解标注的方法表示该方法的部分逻辑在目标方法执行之前执行,另一部分逻辑在目标方法执行之后执行");
        //记录方法开始时间
        long beginTime = System.currentTimeMillis();
        //调用ProceedingJoinPoint对象的proceed方法运行原始方法,返回值为Object类型
        Object result = proceedingJoinPoint.proceed();
        //记录方法结束时间
        long endTime = System.currentTimeMillis();
        //计算并输出方法耗时
        log.info(proceedingJoinPoint.getSignature() + "方法耗时{}s",(endTime - beginTime) / 1000.0);
        //返回原始方法执行的结果
        return result;
    }
    @Before("exp()")
    public void Before()
    {
        log.info("11111111111111111Before注解标注的方法是在目标方法执行之前执行的");
    }
    @After("exp()")
    public void After()
    {
        log.info("After注解标注的方法表示在目标方法执行之后执行,不管目标方法是否正常执行都会执行该方法");
    }
    @AfterReturning("exp())")
    public void BeforeReturning()
    {
        log.info("AfterReturning注解标注的方法表示在目标方法执行之后执行该方法，" +
                "注意目标方法必须正常执行完成，如果出现异常就不会执行该方法");
    }
    @AfterThrowing("exp())")
    public void AfterThrowing()
    {
        log.info("AfterThrowing注解标注的方法表示该方法只有在目标方法出现异常时才会执行，否则是不会执行的");
    }
}

