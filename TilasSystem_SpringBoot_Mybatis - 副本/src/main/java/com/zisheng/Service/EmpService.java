package com.zisheng.Service;

import com.zisheng.pojo.Emp;
import com.zisheng.pojo.PageResult;

import java.time.LocalDate;

public interface EmpService {
    //根据条件查询员工信息
    PageResult searchByPage(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);
    //根据ID删除员工信息
    void deleteByConditions(Integer[] ids);

    void insertEmp(Emp emp);
    Emp getById(Integer id);

    void modify(Emp emp);
}
