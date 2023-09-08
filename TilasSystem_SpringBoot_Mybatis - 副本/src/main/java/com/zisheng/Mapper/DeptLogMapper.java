package com.zisheng.Mapper;

import com.zisheng.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptLogMapper {
    @Insert("insert into deplog_table(create_time, log_message) values(#{createTime},#{logMessage})")
    void writeLog(DeptLog deptLog);
}
