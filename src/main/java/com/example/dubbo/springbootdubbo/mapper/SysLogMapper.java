package com.example.dubbo.springbootdubbo.mapper;

import com.example.springbootdubbo.po.SysLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysLogMapper {

    //插入日志
    int insertSysLog(SysLog sysLog);

    //查询日志
    List<SysLog> queryLog(SysLog sysLog);
}
