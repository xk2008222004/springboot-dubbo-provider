package com.example.dubbo.springbootdubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.springbootdubbo.mapper.StudentMapper;
import com.example.dubbo.springbootdubbo.po.Student;
import com.example.springbootdubbo.po.StudentEx;
import com.example.springbootdubbo.service.StudentExService;
import com.example.springbootdubbo.service.StudentService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = StudentService.class,version = "1.0.0" ,timeout = 15000)
public class StudentServiceImpl   implements StudentService {

    private static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public String getStudent() {
        Student student = studentMapper.getStudent(2);
        String name = null;
        logger.info("student={name="+(name=student.getName()));
        return name;
    }


}
