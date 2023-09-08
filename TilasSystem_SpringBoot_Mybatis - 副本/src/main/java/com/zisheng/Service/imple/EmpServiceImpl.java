package com.zisheng.Service.imple;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zisheng.Annotations.InupdeAnnotation;
import com.zisheng.Mapper.EmpMapper;
import com.zisheng.Service.EmpService;
import com.zisheng.pojo.Emp;
import com.zisheng.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired//采用IOC容器自动注入该类型的bean对象，赋值给该变量
    private EmpMapper empMapper;

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    @Override
    public PageResult searchByPage(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //调用PageHelper插件的startPage方法设置分页参数
        PageHelper.startPage(page,pageSize);
        //调用Mapper接口的方法，将数据封装在集合当中返回
        List<Emp> list = empMapper.list(name,gender,begin,end);
        //将集合对象强制转换成转换成Page对象
        Page<Emp> page1 = (Page<Emp>) list;
        //调用Page对象的方法获取查询到数据的个数以及查询到的数据，然后将个数及其查询数据封装成一个对象返回
        PageResult pageResult = new PageResult(page1.getTotal(),page1.getResult());
        return pageResult;
    }

    /**
     * 根据id删除员工的信息
     * @param ids
     */
    @Override
    public void deleteByConditions(Integer[] ids) {
        empMapper.deleteByIds(ids);
    }

    /**
     * 新增员工信息
     * @param emp
     */
    @Override
    public void insertEmp(Emp emp) {
        /*设置插入时间和修改时间为本地时间*/
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insertEmp(emp);
    }

    /**
     * 根据id查询员工的信息
     * @param id
     * @return
     */
    @Override
    @InupdeAnnotation
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 修改员工的信息
     * @param emp
     */
    @Override
    public void modify(Emp emp) {
//        emp.setPassword("121000");
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.modifyEmp(emp);
    }
}
