package com.nd.entity;
/*
 * Created with IntelliJ IDEA.
 * ClassName: MonthRecord
 * User: 123
 * Date: 2022/7/17
 * Time: 18:07
 * Description:
 */

import java.io.Serializable;

public class MonthRecord implements Serializable {
    private Integer month;//月份
    private Integer sumAmount;//总销售额

    public MonthRecord(Integer month, Integer sumAmount) {
        this.month = month;
        this.sumAmount = sumAmount;
    }

    public MonthRecord() {
    }

    @Override
    public String toString() {
        return "MonthRecord{" +
                "month=" + month +
                ", sumAmount=" + sumAmount +
                '}';
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(Integer sumAmount) {
        this.sumAmount = sumAmount;
    }
}
