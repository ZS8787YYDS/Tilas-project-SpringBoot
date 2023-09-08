package com.zisheng.controller;

import com.zisheng.Service.LoginService;
import com.zisheng.Utils.JwtUtils;
import com.zisheng.pojo.Emp;
import com.zisheng.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtUtils jwtUtils;
    @PostMapping
    public Result login(@RequestBody Emp emp)
    {
        log.info("接收到用户名：{}，密码：{}",emp.getUsername(),emp.getPassword());
        Emp target = loginService.login(emp);
        if(target != null)
        {
            Map<String,Object> map = new HashMap<>();
            map.put("id",target.getId());
            map.put("name",target.getName());
            map.put("username",target.getUsername());
            //生成JWT令牌，返回给服务端
            String JWTStr = jwtUtils.generateJWT(map);
            return Result.success(JWTStr);
        }
        //登陆失败，返回错误结果
        return Result.error("用户名或密码错误");
    }
}
