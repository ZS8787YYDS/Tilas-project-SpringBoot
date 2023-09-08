package com.zisheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan//由于Filter是javaWeb三大组件之一，并不是SpringBoot提供的，因此需要加入ServletComponentScan注解来开启Servlet组件支持
@SpringBootApplication
public class TilasSystemSpringBootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(TilasSystemSpringBootMybatisApplication.class, args);
    }

}
