package com.example.dubbo.springbootdubbo.po;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Alias("sysConfig")
public class SysConfig implements Serializable {

    private String variable;

    private String value;

    private Date setTime;

    private String setBy;

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getSetTime() {
        return setTime;
    }

    public void setSetTime(Date setTime) {
        this.setTime = setTime;
    }

    public String getSetBy() {
        return setBy;
    }

    public void setSetBy(String setBy) {
        this.setBy = setBy;
    }

    public SysConfig(String variable, String value, Date setTime, String setBy) {
        this.variable = variable;
        this.value = value;
        this.setTime = setTime;
        this.setBy = setBy;
    }

    public SysConfig() {
    }

    @Override
    public String toString() {
        return "SysConfig{" +
                "variable='" + variable + '\'' +
                ", value='" + value + '\'' +
                ", setTime=" + setTime +
                ", setBy='" + setBy + '\'' +
                '}';
    }
}
