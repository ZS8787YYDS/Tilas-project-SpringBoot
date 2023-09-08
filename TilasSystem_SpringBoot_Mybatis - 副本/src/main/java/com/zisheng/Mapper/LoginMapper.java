package com.zisheng.Mapper;

import com.zisheng.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("select * from emp_table where username = #{username} and password = #{password}")
    Emp login(Emp emp);
}
