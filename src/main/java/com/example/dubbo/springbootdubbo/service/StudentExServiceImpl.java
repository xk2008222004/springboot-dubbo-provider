package com.example.dubbo.springbootdubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.springbootdubbo.mapper.StudentMapper;
import com.example.dubbo.springbootdubbo.po.Student;
import com.example.springbootdubbo.po.StudentEx;
import com.example.springbootdubbo.service.StudentExService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = StudentExService.class,version = "1.0.0",timeout = 15000)
public class StudentExServiceImpl implements StudentExService {

    private static Logger logger = LoggerFactory.getLogger(StudentExServiceImpl.class);

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public StudentEx getStudentById(Long aLong) {
        Student student = studentMapper.getStudent(Integer.valueOf(aLong+""));
        String name = null;
        if(student==null){
            logger.info("未找到用户，id="+aLong);
            return null;
        }
        logger.info("student={name="+(name=student.getName()));
        StudentEx ex = new StudentEx();
        ex.setAge(student.getAge());
        ex.setId(student.getId());
        ex.setName(student.getName());
        return ex;
    }

    @Override
    public String getStudent() {
        return null;
    }
}
