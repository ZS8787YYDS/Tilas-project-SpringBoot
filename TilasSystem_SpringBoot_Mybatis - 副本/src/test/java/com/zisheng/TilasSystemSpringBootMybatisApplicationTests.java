package com.zisheng;

import com.zisheng.Service.DeptService;
import com.zisheng.Service.EmpService;
import com.zisheng.pojo.Dep;
import com.zisheng.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest
public class TilasSystemSpringBootMybatisApplicationTests {
    //创建日志记录对象，用于记录日志
    private static final Logger log = LoggerFactory.getLogger(TilasSystemSpringBootMybatisApplicationTests.class);
    @Autowired
    private EmpService empService;//采用IOC容器的方式自动注入该类型的bean对象，赋值给该变量
    @Autowired
    private DeptService deptService;
    @Test
    public void testFindEmp()
    {
        Emp emp = empService.getById(5);
        log.info("查询到的员工信息：{}",emp);
    }
    @Test
    public void testDept()
    {
        deptService.updateById(2);
        Dep dept = deptService.findDept(2);
        log.info("部门id为{}的信息为",2);
    }
    @Test
    public void createFile() throws IOException {
        File file = new File("D:/gitRepository");
        if(!file.exists())
        {
            System.out.println(file.mkdir());
//            System.out.println(file.delete());
//            System.out.println(file.createNewFile());
        }
    }
}
