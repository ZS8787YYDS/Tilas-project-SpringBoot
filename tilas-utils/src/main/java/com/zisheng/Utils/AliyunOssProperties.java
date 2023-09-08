package com.zisheng.Utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data //为该类的属性添加get/set方法
@Component // 将该类交给IOC容器进行管理，成为IOC容器的bean对象
@ConfigurationProperties(prefix = "aliyun.oss") //设置前缀，在配置文件中自动获取属性值封装在对象的属性中。
                                                //注意，必须保持属性名和配置文件中的属性名保持一致
public class AliyunOssProperties {
    private String endPoint;
    private String accessKeyId;
    private String accessKeyPassword;
    private String bucketName;
    private String objectName;
}
