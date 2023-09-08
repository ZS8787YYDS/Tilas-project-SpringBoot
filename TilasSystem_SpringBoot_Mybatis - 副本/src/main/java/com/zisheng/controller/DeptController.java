package com.zisheng.controller;

import com.zisheng.Service.DeptService;
import com.zisheng.pojo.Dep;
import com.zisheng.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@Slf4j//用于记录日志信息
@RestController// 将该类的方法返回的结果转换成JSON格式的字符串返回给前端
@RequestMapping("/depts")//将公共的请求路径提取出来，简化书写
public class DeptController {
    @Autowired
    private DeptService deptService;//创建Service层的实现类对象，采用IOC容器自动注入对应类型的bean对象
    private static final Logger log = LoggerFactory.getLogger(DeptController.class); // 创建日志记录对象.也可以直接在类上加上SLf4J这个注解
    @PostMapping
    //通过@RequestBody注解接收JSON格式的数据，存入到对象对应的属性当中
    public Result addDept(@RequestBody Dep dep)
    {
        log.info("新增部门的名称:{}",dep.getName());
        deptService.addDept(dep);
        return Result.success();
    }
    @PutMapping("/{id}")
    public Result updateDept(@PathVariable Integer id)
    {
        log.info("修改id为{}的部门信息",id);
        deptService.updateById(id);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result deleteDeptById(@PathVariable Integer id) throws Exception {
        log.info("删除id为{}的部门",id);
        deptService.deleteById(id);
        return Result.success();
    }
}
