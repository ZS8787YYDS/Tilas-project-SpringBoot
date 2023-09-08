package com.zisheng.Mapper;

import com.zisheng.pojo.Dep;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     *  查询所有部门
     * @return
     */
    @Select("select * from emp_table")
    List<Dep> list();

    /**
     * 删除指定id的部门
     * @param id
     */
    @Delete("delete from dep_table where id = #{id}")
    void deleteById(Integer id);

    /**
     * 添加部门
     * @param dep
     */
    void addDept(Dep dep);

    /**
     * 根据ID更新部门信息
     * @param dep
     */
    void updateById(Dep dep);

    /**
     * 根据id查询部门的信息
     * @param id
     * @return
     */
    @Select("select * from emp_table where id = #{id}")
    Dep findDept(Integer id);
}
