package com.example.dubbo.springbootdubbo.mapper;

import com.example.dubbo.springbootdubbo.po.StudentStatic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentStaticMapper {

    public StudentStatic getStudentStatic(StudentStatic studentStatic);
}
