package com.example.dubbo.springbootdubbo.mapper;

import com.example.dubbo.springbootdubbo.po.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysConfigMapper {


    public SysConfig getSysConfig(String variable);

    public SysConfig getSysConfigWithParam(@Param("variable") String variable, @Param("value") String value);


    public int insertSysConfig(SysConfig sysConfig);
}
