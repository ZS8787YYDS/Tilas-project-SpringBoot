package com.zisheng.Aop;

import com.alibaba.fastjson.JSONObject;
import com.zisheng.Mapper.OperateLogMapper;
import com.zisheng.Utils.JwtUtils;
import com.zisheng.pojo.OperateLog;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class InUpDeAspect {
    //采用IOC容器的方式注入该类型的bean对象，赋值给该变量
    //注入操作JWT令牌的工具类的对象
    @Autowired
    private JwtUtils jwtUtils;
    //注入请求对象
    @Autowired
    private HttpServletRequest httpServletRequest;
    //注入Mapper接口的实现类对象
    @Autowired
    private OperateLogMapper operateLogMapper;
    //提取切入点表达式，采用annotation切入点表达式，
    @Pointcut("@annotation(com.zisheng.Annotations.InupdeAnnotation)")
    private void exp(){}
    //通过类似方法调用的方式引入切入点表达式
    @Around("exp()")
    public Object InUpDeAspectMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //获取token请求头的jwt令牌
        String jwt = httpServletRequest.getHeader("token");
        //解析JWT令牌
        Claims parseResult = jwtUtils.parseJWT(jwt);
        //获取键为id的值
        Integer operateUser = (Integer) parseResult.get("id");
        //获取本地日期时间对象
        LocalDateTime operateTime = LocalDateTime.now();
        //获取目标对象的所在类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        //获取目标方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        //获取目标方法的参数
        Object[] methodParams = proceedingJoinPoint.getArgs();
        //获取方法开始时间毫秒值
        long startTime = System.currentTimeMillis();
        //调用原始方法执行，返回值为result
        Object result = proceedingJoinPoint.proceed();
        //获取方法结束时间毫秒值
        long endTime = System.currentTimeMillis();
        //计算出方法的执行时间，单位为ms
        long costTime = endTime - startTime;
        //创建操作日志记录对象
        OperateLog operateLog  = new OperateLog();
        //对该对象进行初始化
        operateLog.setOperateUser(operateUser);
        operateLog.setOperateTime(operateTime);
        operateLog.setClassName(className);
        operateLog.setMethodName(methodName);
        operateLog.setMethodParams(Arrays.toString(methodParams));
        //调用JSONObject类的toJSOMString方法，将对象转换成JSON字符串
        operateLog.setReturnValue(JSONObject.toJSONString(result));
        operateLog.setCostTime(costTime);
        //调用Mapper接口的方法将数据信息插入到对应的数据库表中
        operateLogMapper.insertInfo(operateLog);
        return result;
    }
}
