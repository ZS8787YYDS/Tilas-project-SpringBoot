package com.zisheng.Service.imple;

import com.zisheng.Annotations.InupdeAnnotation;
import com.zisheng.Mapper.DeptMapper;
import com.zisheng.Mapper.EmpMapper;
import com.zisheng.Service.DeptLogService;
import com.zisheng.Service.DeptService;
import com.zisheng.pojo.Dep;
import com.zisheng.pojo.DeptLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;
    /**
     * 查询所有部门的信息
     * @return
     */
    @Override
    public List<Dep> list() {
        return deptMapper.list();
    }

    @Override
    public Dep findDept(Integer id) {
        return deptMapper.findDept(id);
    }

    /**
     * 根据ID删除部门信息
     * @param id
     */
    @Override
    @InupdeAnnotation
    //将该方法交给Spring进行事务管理,方法执行之前开启事务，方法成功执行提交事务，
    // 一旦出现了异常，就会回滚事务，保证数据库中数据的一致性
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) throws Exception {
        try {
            deptMapper.deleteById(id);
//            int i = 1 / 0;
            empMapper.deleteEmpsById(id);
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setLogMessage("删除的是id为" + id + "的部门");
            deptLog.setCreateTime(LocalDateTime.now());
            //调用Service层记录日志，无论当前方法是否正常执行，都会将当前方法中的事务先挂起，
            // 再创建一个新事务，完成记录日志的任务。日志记录完成之后再恢复当前方法中的事务。
            //因此即使当前方法中出现了异常，最终会回滚事务，仍然不影响日志的记录。
            deptLogService.writeLog(deptLog);
        }
    }

    /**
     * 添加部门的信息
     * @param dep
     */
    @Override
    @InupdeAnnotation
    public void addDept(Dep dep) {
        dep.setId(8);
        dep.setCreateTime(LocalDateTime.now());
        dep.setUpdateTime(LocalDateTime.now());
        deptMapper.addDept(dep);
    }

    /**
     * 根据ID修改部门的信息
     * @param id
     */
    @Override
    @InupdeAnnotation
    public void updateById(Integer id) {
        Dep dep = new Dep();
        dep.setId(id);
        dep.setName("摆烂部");
        dep.setUpdateTime(LocalDateTime.now());
        deptMapper.updateById(dep);
    }
}
