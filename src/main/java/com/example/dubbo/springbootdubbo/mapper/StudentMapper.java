package com.example.dubbo.springbootdubbo.mapper;

import com.example.dubbo.springbootdubbo.po.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {

    public Student  getStudent(int id);


    public int addStudent(Student st);


    public int delStudent(int id);

    public List<Student> getStudentByName(String name);

}
