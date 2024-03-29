package com.nd.sl.producer.bean;


import com.nd.sl.common.bean.Data;


public class user extends Data {
    private String useid;//用户id
    private String name;//用户姓名

    public String getUseid() {
        return useid;
    }

    public void setUseid(String useid) {
        this.useid = useid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setValue(Object val){
        context=(String)val;
        String[] split=context.split("\t");
        setUseid(split[0]);
        setName(split[1]);
    }

    @Override
    public String toString() {
        return useid + "\t" + name ;
    }
}
