package com.nd.sl.producer.io;

import com.nd.sl.common.bean.DataOut;

import java.io.*;

public class LocalFileDataOut implements DataOut {
    private PrintWriter writer=null;//打印输出流
    public LocalFileDataOut(String path){
        setPath(path);
    }
    @Override
    public void setPath(String path) {
        try {
            writer=new PrintWriter(new OutputStreamWriter(new FileOutputStream(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void write(Object obj) throws Exception {
        write(obj.toString());
    }

    @Override
    public void write(String data) throws Exception {
        writer.println(data);
        writer.flush();//把流中文件放到文件中
    }

    @Override
    public void close() throws IOException {
        if (writer!=null){
            writer.close();
        }
    }
}
