package com.example.dubbo.springbootdubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.springbootdubbo.mapper.SysLogMapper;
import com.example.springbootdubbo.po.SysLog;
import com.example.springbootdubbo.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Service(interfaceClass = SysLogService.class,version = "1.0.0",timeout = 15000)
public class SysLogServiceImpl implements SysLogService {


    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public int insertSysLog(SysLog sysLog) {
        return sysLogMapper.insertSysLog(sysLog);
    }

    @Override
    public List<SysLog> queryLog(SysLog sysLog) {
        return sysLogMapper.queryLog(sysLog);
    }
}
