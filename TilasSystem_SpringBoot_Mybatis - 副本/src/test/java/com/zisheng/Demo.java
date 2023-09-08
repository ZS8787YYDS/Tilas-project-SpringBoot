package com.zisheng;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Demo {
    private static final Logger log = LoggerFactory.getLogger(Demo.class);
    public static void main(String[] args) throws Exception {
        // 地域节点
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 密钥ID
        String access_Id = "LTAI5tSjn1xbPtpEwomEYYKa";
        // 密钥密码
        String access_password = "HRkfcvWRxcG8TpyYSEmfSHn45Fjhkw";
        // 指定上传到的Bucket容器名称
        String bucketName = "web-tilas-yyds";
        // 指定上传到的Bucket容器中文件的名称
        String objectName = "images/certificate.jpg";
        // 指定上传的本地文件路径
        String filePath= "C:\\Users\\lenovo\\Pictures\\certificate.jpg";
        // 创建OSSClient对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, access_Id,access_password);

        try {
            //创建文件字节输入流管道与源文件接通
            InputStream inputStream = new FileInputStream(filePath);
            //调用OSSClient对象的putObject方法,将文件上传到OSS当中
           ossClient.putObject(bucketName, objectName, inputStream);
            // 创建PutObject请求。
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    /**
     * 生成JWT令牌
     */
    @Test
    public  void testdGenreateJWT()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("name","Tom");
        map.put("gender",1);
        String jwtStr = Jwts.builder().signWith(SignatureAlgorithm.HS256,"zisheng")//设置签名算法和密钥
                .setClaims(map)//设置自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置截止时间
                .compact();//生成JWt令牌
        System.out.println(jwtStr);
        /**
         * 生成的令牌为：
         * eyJhbGciOiJIUzI1NiJ9.eyJnZW5kZXIiOjEsIm5hbWUiOiJUb20iLCJleHAiOjE2OTE4MTQ2MjV9.4_MsFrQYCpnsPfMrPiTMBCFSmvbcx9cL-nxnvhmACl4
         */
    }

    /**
     * 解析JWT令牌
     */
    @Test
    public void TestParseJWT()
    {
        Claims claims = Jwts.parser().setSigningKey("zisheng")//设置签名密钥
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJnZW5kZXIiOjEsIm5hbWUiOiJUb20iLCJleHAiOjE2OTE4MjU2NzN9." +
                        "FYAgDiQaJO-QAmTyhr87MXHbYikR8nXVV0Fn9aUiGS8")//解析JWT令牌
                .getBody();//获取解析之后的信息
        log.info("解析的结果为：{}",claims);
        System.out.println(claims);
    }
} 