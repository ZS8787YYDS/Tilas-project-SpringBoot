package com.zisheng.Service.imple;

import com.zisheng.Mapper.DeptLogMapper;
import com.zisheng.Service.DeptLogService;
import com.zisheng.pojo.DeptLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service//将该接口的实现类对象交给IOC容器进行管理
public class DeptLogServiceImpl implements DeptLogService {
    //采用IOC容器的方式自动注入该类型的bean对象，赋值给该变量
    @Autowired
    private DeptLogMapper deptLogMapper;
    @Override
    //设置propagation的值为requires.new，表示在调用该方法的的方法中，
    //不管是否有事务，都会新创建一个额外的事务
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void writeLog(DeptLog deptLog) {
        deptLogMapper.writeLog(deptLog);
    }
}
