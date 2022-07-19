package com.nd.entity;
/*
 * Created with IntelliJ IDEA.
 * ClassName: ObjectRecord
 * User: 123
 * Date: 2022/7/17
 * Time: 18:08
 * Description:
 */

import java.io.Serializable;

public class ObjectRecord implements Serializable {
    private String pid;
    private String pname;
    private Integer sumamount;

    @Override
    public String toString() {
        return "ObjectRecord{" +
                "pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", sumamount=" + sumamount +
                '}';
    }

    public ObjectRecord() {
    }

    public ObjectRecord(String pid, String pname, Integer sumamount) {
        this.pid = pid;
        this.pname = pname;
        this.sumamount = sumamount;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getSumamount() {
        return sumamount;
    }

    public void setSumamount(Integer sumamount) {
        this.sumamount = sumamount;
    }
}
