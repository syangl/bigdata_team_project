package com.nd.sl.producer.io;


import com.nd.sl.common.bean.Data;
import com.nd.sl.common.bean.DataIn;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LocalFileDataIn implements DataIn {
    BufferedReader read=null;
    public LocalFileDataIn(String path) throws FileNotFoundException, UnsupportedEncodingException {
        setPath(path);
    }

    // 读入文件数据
    @Override
    public void setPath(String path) throws FileNotFoundException, UnsupportedEncodingException {
        read=new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
    }

    @Override
    public Object read() throws IOException {
        return null;
    }

    //读取数据，返回集合
    @Override
    public <T extends Data> List<T> read(Class<T> clazz) throws IOException {
        //返回集合
        List<T>ts=new ArrayList<>();
        String line=null;
        while ((line=read.readLine())!=null){
            //获取实例对象
            try {
               T t = clazz.newInstance();
               //把读取的一行数据对象放入t实例中
               t.setValue(line);
               //把ts实例对象放入集合中
                ts.add(t);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return ts;
    }

    @Override
    public void close() throws IOException {
        if (read!=null){
            read.close();
        }
    }
}
