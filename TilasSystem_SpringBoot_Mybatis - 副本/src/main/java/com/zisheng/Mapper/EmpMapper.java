package com.zisheng.Mapper;

import com.zisheng.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    //根据条件查询员工信息
//    @Select("select * from emp_table")
    List<Emp> list(String name, Short gender, LocalDate start, LocalDate end);
    //根据ID删除员工信息
    void deleteByIds(Integer[] ids);
    //插入员工信息
    void insertEmp(Emp emp);
    @Select("select * from emp_table where id = #{id}")
    Emp getById(Integer id);

    void modifyEmp(Emp emp);

    /**
     *  根据部门ID删除员工
     * @param id
     */
    @Delete("delete from emp_table where dep_id = #{id}")
    void deleteEmpsById(Integer id);
}
