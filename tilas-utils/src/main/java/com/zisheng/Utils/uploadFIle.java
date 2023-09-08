package com.zisheng.Utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
@Component//将该类的对象交给IOC容器进行管理
public class uploadFIle {
//    //OSS对象存储服务的域名
////    @Value("${aliyun.oss.endPoint}")
//    private String endPoint;
//    //密钥ID
////    @Value("${aliyun.oss.accessKeyId}")
//    private String accessKeyId;
//    //密钥密码
////    @Value("${aliyun.oss.accessKeyPassword}")
//    private String accessKeyPassword;
//    //OSS对象存储服务的bucket容器名称
////    @Value("${aliyun.oss.bucketName}")
//    private String bucketName;
//    //OSS对象存储服务bucket容器中对象的名称，因为bucket容器中存储的就是对象，是一个存储对象的容器
////    @Value("${aliyun.oss.objectName}")
//    private String objectName;
    // 通过IOC容器的方式注入该类型的bean对象，赋值给该变量
    @Autowired
    private AliyunOssProperties aliyunOssProperties;
    public String upload(MultipartFile file) throws IOException {
        // 调用get方法获取属性值
        String endPoint = aliyunOssProperties.getEndPoint();
        String accessKeyId = aliyunOssProperties.getAccessKeyId();
        String accessKeyPassword = aliyunOssProperties.getAccessKeyPassword();
        String bucketName = aliyunOssProperties.getBucketName();;
        String objectName = aliyunOssProperties.getObjectName();
        //获取文件的原始文件名称
        String originalFileName = file.getOriginalFilename();
        //创建文件的新名称
        String newFileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));
        //创建OSS对象
        OSS ossClient = new OSSClientBuilder().build(endPoint,accessKeyId,accessKeyPassword);
        //调用对象的putObject方法，上传文件到OSS对象存储服务中
        ossClient.putObject(bucketName,objectName + newFileName,file.getInputStream());
        /*https://web-tilas-yyds.oss-cn-beijing.aliyuncs.com/images/certificate.jpg*/
        //返回文件的url地址
        String url = "https://" + bucketName + "." + endPoint.substring(endPoint.indexOf("o")) + "/" + (objectName + newFileName);
        return url;
    }
}
