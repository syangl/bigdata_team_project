package com.nd.sl.analysis.mapper;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class AnalysisTextMapper extends TableMapper<Text,Text> {
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context)
            throws IOException, InterruptedException {
        /*
        1_05_0123456789_20210105
         */
        byte[] name=value.getValue(Bytes.toBytes("sinfo"),Bytes.toBytes("name"));
        String pname=new String(name,0,name.length,"UTF-8");
        String num=new String(value.getValue(Bytes.toBytes("sinfo"),Bytes.toBytes("num")));
        String unitprice=new String(value.getValue(Bytes.toBytes("sinfo"),Bytes.toBytes("unitprice")));
        String rowKey= Bytes.toString(key.get());
        String[] values = rowKey.split("_");
        String pid=values[1];
        String date=values[3];
        String cid=values[2];
        String month=date.substring(4,6);
        // 向内存缓冲区写出
        context.write(new Text(pid+"_"+pname+"_"+month),new Text(num+"_"+unitprice));//按月分类
    }
}
