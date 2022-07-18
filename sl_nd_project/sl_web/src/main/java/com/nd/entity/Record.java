package com.nd.entity;
/*
 * Created with IntelliJ IDEA.
 * ClassName: Record
 * User: 123
 * Date: 2022/7/17
 * Time: 9:26
 * Description:销售记录实体类
 */

import java.io.Serializable;

public class Record implements Serializable {
    private Integer id;//自增id
    private String pid;//商品id
    private String pname;//商品名称
    private Integer month;//月份1-12
    private Integer pnum;//销售数量
    private Integer amount;//销售金额

    public Record(Integer id, String pid, String pname, Integer month, Integer pnum, Integer amount) {
        this.id = id;
        this.pid = pid;
        this.pname = pname;
        this.month = month;
        this.pnum = pnum;
        this.amount = amount;
    }

    public Record() {
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", month=" + month +
                ", pnum=" + pnum +
                ", amount=" + amount +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getPnum() {
        return pnum;
    }

    public void setPnum(Integer pnum) {
        this.pnum = pnum;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
