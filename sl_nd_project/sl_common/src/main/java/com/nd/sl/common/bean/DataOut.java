package com.nd.sl.common.bean;

import java.io.Closeable;

public interface DataOut extends Closeable {
    void setPath(String path);
    //写出数据
    void write(Object obj)throws Exception;
    void write(String obj)throws Exception;
}
