package com.zisheng.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    @Value("${signKey}")//通过注解的形式引入配置文件中的key为signKey的值，赋值给该变量
    private String signKey;
    private Long endTime =   3600 * 1000 * 24L;
    //生成JWT令牌
    public String generateJWT(Map<String,Object> map)
    {
        String jwtStr = Jwts.builder().signWith(signatureAlgorithm,signKey).setClaims(map).
                setExpiration(new Date(System.currentTimeMillis() + endTime)).compact();
        return jwtStr;
    }
    /*解析JWT令牌*/
    public Claims parseJWT(String JWTStr)
    {
        Claims claims = Jwts.parser().setSigningKey(signKey).parseClaimsJws(JWTStr).getBody();
        return claims;
    }
}
