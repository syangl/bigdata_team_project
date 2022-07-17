package com.nd.sl.analysis.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AnalysisTextReducer extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //统计月销量
        int pnum=0;
        //统计月销售额
        int amount=0;
        //测试key值正确与否
        String keyString=key.toString();
        String[] keyValues=keyString.split("_");
        String pid=keyValues[0];
        String pname=keyValues[1];
        String month=keyValues[2];
        for (Text value : values) {
            String info=value.toString();
            String[] infos=info.split("_");
            int num=Integer.parseInt(infos[0]);
            int unitprice=Integer.parseInt(infos[1]);
            pnum+=num;
            amount+=num*unitprice;
        }
        //打印测试
        System.out.println(pid+"_"+pname+"_"+month+"_"+pnum+"_"+amount);
        context.write(key,new Text(pnum+"_"+amount));
    }
}
