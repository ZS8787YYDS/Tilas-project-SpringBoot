package com.zisheng.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zisheng.Utils.JwtUtils;
import com.zisheng.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义一个拦截器，并且将这个拦截器对象交给IOC容器进行管理
 */
@Component
public class DemoInterceptor  implements HandlerInterceptor {
    @Autowired
    private JwtUtils jwtUtils;
    //定义一个日志记录对象，用于记录日志
    private static final Logger log = LoggerFactory.getLogger(DemoInterceptor.class);
    @Override
    //在资源方法执行之前执行，返回true，执行资源方法，返回false，不执行资源方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求路径,调用getRequestURL方法获StringBuffer字符串对象，调用toString方法获取String字符串对象
        String url = request.getRequestURL().toString();
        //判断请求路径中是否包含login字符串，即判断是否为登录请求，是的话直接放行
        if(url.contains("login"))
        {
            log.info("登录请求，直接放行~~~");
            return true;
        }
        //说明不是登陆请求
        //获取JWT令牌。调用请求对象的getHeader方法获取token请求头的值，即JWT字符串
        String jwt = request.getHeader("token");
        log.info("jwt令牌：{}",jwt);
        //调用StringUtils工具类的hasLength方法，判断令牌是否为空字符串或者为空
        if(!StringUtils.hasLength(jwt))
        {
            //jwt令牌为空，响应错误结果信息
            log.info("jwt令牌为空，响应未登录错误结果信息");
            // 创建Result对象，设置返回的信息为未登录状态
            Result result = Result.error("NOT_LOGIN");
            //将对象转换成JSON格式的字符串,调用JSONObject类的toJSONString方法
            String JSONStr = JSONObject.toJSONString(result);
            //将JSON格式的字符串响应给前端，调用HttpServletResponse对象的getWriter方法获取字符打印流，再调用write方法将JSON字符串响应给会给前端
            response.getWriter().write(JSONStr);
            return false;//由于JWT令牌不合法，返回false，不放行，响应已经结束
        }
        //既不是登录请求，jwt令牌也不为空。解析jwt令牌
        // 只要再解析过程中不报错就说明解析成功,JWT令牌合法
        try {
            jwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            // 如果在解析JWT令牌的时候出错了，说明JWT不合法,返回false，不执行资源方法
            e.printStackTrace();
            log.info("解析jwt出现异常，解析失败");
            // 创建Result对象
            Result result = Result.error("NOT_LOGIN");
            // 将对象转换为JSON格式的字符串
            String JSONStr = JSONObject.toJSONString(result);
            // 获取字符打印流对象，调用write方法将信息响应给前端
            response.getWriter().write(JSONStr);
            return false;
        }
        log.info("令牌合法，放行！！！");
        return true;//返回true，代表拦截到请求之后会进行放行，登陆成功，即执行对应的资源
    }

    @Override
    //在资源方法执行之后执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle方法执行了！！！");
    }

    @Override
    //在视图渲染完毕之后执行，即最后执行该方法
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion方法执行了~~~");
    }
}
