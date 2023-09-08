package com.zisheng.Service;

import com.zisheng.Annotations.AOPAnnotation;
import com.zisheng.pojo.Dep;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门的信息
     * @return
     */
    List<Dep> list();

    /**
     * 根据id查询部门的信息
     * @param id
     * @return
     */
    Dep findDept(Integer id);
    /**
     * 根据id删除部门
     * @param id
     * @throws Exception
     */
    void deleteById(Integer id) throws Exception;

    /**
     * 新增部门的信息
     * @param dep
     */
    void addDept(Dep dep);

    /**
     * 更新部门信息
     * @param id
     */
    @AOPAnnotation
    void updateById(Integer id);
}
