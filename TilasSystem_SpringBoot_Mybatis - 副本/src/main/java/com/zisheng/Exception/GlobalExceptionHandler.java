package com.zisheng.Exception;

import com.zisheng.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 简单来说就就是：创建一个类，加上@RestControllerAdvice注解，表明该类为全局异常处理器.
 * 创建一个方法，加上ExceptionHandler注解，定义可以处理异常的类型，这样，当发生异常的时候，就会执行对应的方法
 */
@RestControllerAdvice//表明该类为全局异常处理器
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)//表明可以处理所有的异常
    // 当产生异常的时候就会调用这个方法
    public Result handleException(Exception e)
    {
        e.printStackTrace();//将异常信息打印出来
        //返回一个Result对象，会自动转换成JSON格式的字符串响应给前端
        return Result.error("对不起，操作失败，请你联系管理员！！！");
    }
}
