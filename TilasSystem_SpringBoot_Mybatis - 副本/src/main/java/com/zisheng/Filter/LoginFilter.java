package com.zisheng.Filter;

import com.alibaba.fastjson.JSONObject;
import com.zisheng.Utils.JwtUtils;
import com.zisheng.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Autowired
    private JwtUtils jwtUtils;//利用IOC容器自动注入该类型的bean对象，赋值给该变量
    //创建日志记录对象，用于记录日志
    private static final Logger log = LoggerFactory.getLogger(LoginFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将请求对象强制转换成HTTP请求对象
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //将响应对象强制转换成HTTP响应对象
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        //获取请求的URL地址，调用HTTP请求对象的getRequestURL的方法获得StringBuffer字符串对象，再调用toString方法转换成String字符串对象
        String url = httpServletRequest.getRequestURL().toString();
        log.info("url:{}",url);
        //如果说请求路径中包含login字段，说明请求为登录请求，直接放行即可，去访问登陆资源
        if(url.contains("login"))
        {
            log.info("请求为登录请求~~~");
            filterChain.doFilter(servletRequest,servletResponse);
            //登陆操作完成之后注意要return返回，因为下面代码不需要执行
            return;
        }
        //获取请求头中名称为token存储的JWT令牌
        String jwt = httpServletRequest.getHeader("token");
        log.info("jwt令牌：{}",jwt);
        //如果说令牌字符串为空或字符串为为字符串的话，就返回未登录的错误信息
        if(!StringUtils.hasLength(jwt))
        {
            log.info("jwt令牌为空，将返回未登录结果！！！");
            //创建Result对象，封装错误信息
            Result result = Result.error("NOT_LOGIN");
            //将对象转化为JSON格式的字符串,利用阿里云提供的fastJSON进行转换
            String JSONStr = JSONObject.toJSONString(result);
            //利用响应对象的getWriter方法获取打印流管道，将JSON格式的字符串响应给前端
            httpServletResponse.getWriter().write(JSONStr);
            //响应数据之后注意return，因为之后的代码是解析JWT令牌的，不需要执行
            return;
        }
        try {
            //解析JWT令牌，如果说解析过程中出错了，说明解析失败，需要返回未登录的错误信息
            jwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            //解析失败
            log.info("令牌解析失败，返回未登陆的错误信息");
            Result result = Result.error("NOT_LOGIN");
            //将对象转化为JSON格式的数据,利用阿里云提供的fastJSON进行转换
            String JSONStr = JSONObject.toJSONString(result);
            //将JSON格式的数据响应给前端
            httpServletResponse.getWriter().write(JSONStr);
            return;
        }
        log.info("令牌合法，放行！！！");
        //jWT令牌解析成功，放行，继续访问其他资源
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
