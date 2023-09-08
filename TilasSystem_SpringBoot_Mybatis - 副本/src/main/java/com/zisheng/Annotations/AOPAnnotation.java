package com.zisheng.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//Target元注解，指定注解的作用范围为方法上
@Retention(RetentionPolicy.RUNTIME)//Retention元注解，指定注解的保留周期，保留到运行阶段
public @interface AOPAnnotation {
}
