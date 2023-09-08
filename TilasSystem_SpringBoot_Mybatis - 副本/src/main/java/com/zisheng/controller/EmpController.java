package com.zisheng.controller;

import com.zisheng.Service.EmpService;
import com.zisheng.pojo.Emp;
import com.zisheng.pojo.PageResult;
import com.zisheng.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequestMapping("/emps")
@Slf4j//Slfj注解，会自动创建日志记录对象log，可以通过log对象记录日志
@RestController//将函数返回的信息作为响应结果
public class EmpController {
    @Autowired//采用IOC容器的方式提供该类型的bean对象，赋值给该变量
    private EmpService empService;
//    指定请求路径和请求方式
//    法一：使用RequestMapping注解
//    @RequestMapping(value = "/emps",method = RequestMethod.GET)
//    法二：直接使用GetMapping注解
    @GetMapping
    //使用@RequestParam注解设置默认值，即如果没有接收到参数值的话默认的值
    //也可以自己进行判断，即如果形参的值为null的话，说明没有接收到值，那么就赋予默认值就可以了
    public Result pageSearch(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             String name, Short gender, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                             @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end)
    {
        //调用日志记录对象，记录日志
        log.info("分页操作，页码：{},每页显示的记录数：{},查询名称：{},查询性别：{},开始日期：{},结束日期：{}",page,pageSize,name,gender,begin,end);
        //调用Service层的方法获取处理之后的结果，封装之后响应给前端
        PageResult pageResult = empService.searchByPage(page,pageSize,name,gender,begin,end);
        return Result.success(pageResult);
    }
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable Integer[] ids)
    {
        log.info("删除id属于{}的员工",ids);
        empService.deleteByConditions(ids);
        return Result.success();
    }
    @PostMapping
    public Result insertEmp(@RequestBody Emp emp)
    {
        log.info("新增的员工信息为：{}",emp);
        empService.insertEmp(emp);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id)
    {
        log.info("根据id查询信息，id：{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }
    @PutMapping
    public Result modifyEmp(@RequestBody Emp emp)
    {
        log.info("接收到的数据为：{}",emp);
        empService.modify(emp);
        return Result.success();
    }
}
