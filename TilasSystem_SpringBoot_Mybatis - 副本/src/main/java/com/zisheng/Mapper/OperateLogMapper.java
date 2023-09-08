package com.zisheng.Mapper;

import com.zisheng.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {
    @Insert("insert into operatelog_table(operate_user, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateUser},#{operateTime},#{className},#{methodName},#{methodParams},#{returnValue},#{costTime})")
    void insertInfo(OperateLog operateLog);
}
