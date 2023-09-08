package com.zisheng.Aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
//@Aspect
public class MyAspect02 {
    //通过Pointcut注解提取切入点表达式。
    //execution切入点表达式，根据返回值类型 包名 类名 方法名 方法参数来匹配方法
//    @Pointcut("execution(* com.zisheng.Service.EmpService.getById(Integer)) || execution(* com.zisheng.Service.DeptService.deleteById(Integer))")
    //annotation切入点表达式，根据注解来匹配方法，我们只需要定义一个注解，想匹配哪些方法，直接在方法上加上定义的注解即可，
    //这样就会自动匹配到对应的方法
//    @Pointcut("@annotation(com.zisheng.Annotations.AOPAnnotation)")
//    @Pointcut("execution(* com.zisheng.Service.EmpService.getById(Integer)) || execution(* com.zisheng.Service.DeptService.deleteById(Integer))")
    @Pointcut("@annotation(com.zisheng.Annotations.AOPAnnotation)")
    private void exp(){}
    //前置方法，在目标方法执行之前执行该方法。
    //按照调用方法的形式引入切入点表达式
//    @Before("exp()")
    public void Before(Joinpoint joinpoint)
    {
        log.info("Before方法执行开始....");
    }
    @Around("exp()")
    public Object Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Around方法执行开始！！！");
        //获取目标对象的类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        log.info("目标对象的类名：{}",className);
        //获取目标方法的方法签名
        Signature signature = proceedingJoinPoint.getSignature();
        log.info("目标方法的方法签名：{}",signature);
        //获取目标方法的方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        log.info("目标方法的方法名：{}",methodName);
        //获取目标方法运行时的传入的参数
        Object[] args = proceedingJoinPoint.getArgs();
        log.info("目标方法运行时传入的参数：{}", Arrays.toString(args));
        //执行目标方法.返回值为Object类型的数据
        Object result = proceedingJoinPoint.proceed();
        log.info("目标方法的返回值：{}",result);
        log.info("目标方法执行结束！！！");
        return result;
    }
}
