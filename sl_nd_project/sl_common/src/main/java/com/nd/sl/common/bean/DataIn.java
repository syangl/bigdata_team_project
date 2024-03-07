package com.nd.sl.common.bean;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * IO要手动编写close()方法进行关闭，
 * 然而，每次这些写会造成代码冗余不优雅，自动释放资源Closeable可以使用
 * */
public interface DataIn extends Closeable {// setpath & read
    void setPath(String path) throws FileNotFoundException, UnsupportedEncodingException;
    Object read()throws IOException;
    <T extends Data>List<T> read(Class<T> clazz)throws IOException;

}
