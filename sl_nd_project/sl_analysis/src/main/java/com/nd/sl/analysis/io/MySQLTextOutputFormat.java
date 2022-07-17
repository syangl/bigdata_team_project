package com.nd.sl.analysis.io;

import com.nd.sl.common.util.JdbcUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.PathOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.PathOutputCommitterFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLTextOutputFormat extends OutputFormat<Text,Text> {
    protected static class MySQLRecordWriter extends RecordWriter<Text,Text>{
        private Connection connection=null;
        public MySQLRecordWriter(){
            //获取资源
            connection= JdbcUtil.getConnection();
        }
        /**
         * 输出数据
         * @param key
         * @param value
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        public void write(Text key, Text value) throws IOException, InterruptedException {
            String[] keyValues=key.toString().split("_");
            String[] values=value.toString().split("_");
            String pid=keyValues[0];
            String pname=keyValues[1];
            String month=keyValues[2];
            String pnum=values[0];
            String amount=values[1];
            PreparedStatement ps=null;
            try {
                String insertSQL="insert into tb_record(pid,pname,month,pnum,amount) values(?,?,?,?,?)";
                ps = connection.prepareStatement(insertSQL);
                ps.setString(1,pid);
                ps.setString(2,pname);
                ps.setInt(3,Integer.parseInt(month));
                ps.setInt(4,Integer.parseInt(pnum));
                ps.setInt(5,Integer.parseInt(amount));
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * 释放资源
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        public void close(TaskAttemptContext context) throws IOException, InterruptedException {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public RecordWriter<Text, Text> getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
        return new MySQLRecordWriter();
    }

    @Override
    public void checkOutputSpecs(JobContext context) throws IOException, InterruptedException {

    }
    private PathOutputCommitter committer = null;
    public static Path getOutputPath(JobContext job) {
        String name = job.getConfiguration().get(FileOutputFormat.OUTDIR);
        return name == null ? null: new Path(name);
    }
    //提交mapreduce给hadoop
    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException, InterruptedException {
        if (committer == null) {
            Path output = getOutputPath(context);
            committer = PathOutputCommitterFactory.getCommitterFactory(
                    output,
                    context.getConfiguration()).createOutputCommitter(output, context);
        }
        return committer;
    }
}
