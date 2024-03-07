package com.nd.sl.common.constant;

/**
 * @ClassName: Names
 * @PackageName:com.nd.sl.common.constant
 * @Description: 名称常量枚举类
 * @Author LiuSiyang
 * @Date 2022/7/16 10:18
 * @Version 1.0.0
 */
public enum Names implements Val{
    //kafka topic
    TOPIC("sl"),
    //命名空间
    NAMESPACE("sl"),
    //hbase购买记录表
    TABLE("sl:record"),
    //列族
    CF_INFO("sinfo"),
    ;

    private String name;
    private Names(String name){
        this.name=name;
    }

    @Override
    public void setValue(Object val) {
        this.name=(String) val;
    }

    @Override
    public String getValue() {
        return name;
    }
}
