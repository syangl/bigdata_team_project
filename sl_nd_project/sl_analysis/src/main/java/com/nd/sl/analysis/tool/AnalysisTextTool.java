package com.nd.sl.analysis.tool;

import com.nd.sl.analysis.io.MySQLTextOutputFormat;
import com.nd.sl.analysis.mapper.AnalysisTextMapper;
import com.nd.sl.analysis.reducer.AnalysisTextReducer;
import com.nd.sl.common.constant.Names;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.util.Tool;

public class AnalysisTextTool implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        //获取job（Job对象指定作业执行规范，用它来控制整个作业的运行）
        Job job= Job.getInstance();
        //设置jar位置
        job.setJarByClass(AnalysisTextTool.class);
        //扫描列族
        Scan scan=new Scan();
        scan.addFamily(Bytes.toBytes(Names.CF_INFO.getValue()));
        //设置mapper
        TableMapReduceUtil.initTableMapperJob(
                Names.TABLE.getValue(),
                scan,
                AnalysisTextMapper.class,
                Text.class,
                Text.class,
                job
        );
        //设置reducer
        job.setReducerClass(AnalysisTextReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        //设置输出（本地生产数据-flume采集到kafka-存储在hbase-mapreduce分析-结果输出到mysql数据库）
        job.setOutputFormatClass(MySQLTextOutputFormat.class);
        //提交
        boolean flag = job.waitForCompletion(true);
        if(flag){
            return JobStatus.State.SUCCEEDED.getValue();
        }else{
            return JobStatus.State.FAILED.getValue();
        }
    }

    @Override
    public void setConf(Configuration conf) {

    }

    @Override
    public Configuration getConf() {
        return null;
    }
}
