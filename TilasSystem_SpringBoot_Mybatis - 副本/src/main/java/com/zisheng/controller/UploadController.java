package com.zisheng.controller;

import com.zisheng.Utils.uploadFIle;
import com.zisheng.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j//用于得到日志记录对象，获取日志
@RestController//将函数返回的信息作为相应的结果
public class UploadController {
    //创建工具类的实现类对象，我们采用IOC容器进行自动注入
    @Autowired
    private uploadFIle uploadFile;
    @PostMapping("/uploads")
    public Result unloading(MultipartFile image) throws IOException {
        log.info("上传的文件本地路径：{}",image);
        //调用工具类的方法进行文件上传，返回文件的url地址
        String url = uploadFile.upload(image);
        log.info("文件的url路径为：{}",url);
        return Result.success(url);
    }
}
