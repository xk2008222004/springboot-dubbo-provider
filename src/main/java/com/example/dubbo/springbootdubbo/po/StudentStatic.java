package com.example.dubbo.springbootdubbo.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class StudentStatic implements Serializable {

    private String studentName;

    private int countStu;

    private Timestamp exDate;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getCountStu() {
        return countStu;
    }

    public void setCountStu(int countStu) {
        this.countStu = countStu;
    }

    public Timestamp getExDate() {
        return exDate;
    }

    public void setExDate(Timestamp exDate) {
        this.exDate = exDate;
    }

    @Override
    public String toString() {
        return "StudentStatic{" +
                "studentName='" + studentName + '\'' +
                ", countStu=" + countStu +
                ", exDate=" + exDate +
                '}';
    }
}
