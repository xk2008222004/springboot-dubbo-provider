package com.example.dubbo.springbootdubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.springbootdubbo.service.TeacherService;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = TeacherService.class,version = "1.0.0",timeout = 2000)
public class TeacherServiceImpl implements TeacherService{



    @Override
    public String getTeacher(String s) {
        return "this is another teacher his nanme is "+s;
    }
}
