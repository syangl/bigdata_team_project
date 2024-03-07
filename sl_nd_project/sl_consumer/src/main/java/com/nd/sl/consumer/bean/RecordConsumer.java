package com.nd.sl.consumer.bean;

import com.nd.sl.common.bean.Consumer;
import com.nd.sl.common.constant.Names;
import com.nd.sl.consumer.dao.HBaseDao;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @ClassName: RecordConsumer
 * @PackageName:com.nd.sl.consumer.bean
 * @Description: 购买记录消费者对象
 * @Author LiuSiyang
 * @Date 2022/7/16 10:11
 * @Version 1.0.0
 */
public class RecordConsumer implements Consumer {
    @Override
    public void consumer() throws IOException {
        //创建配置对象（Properties类用于读取Java的配置文件，在Java中，其配置文件常为.properties文件，是以键值对的形式进行参数配置的）
        Properties prop=new Properties();
        prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("consumer.properties"));
        //获取flume采集的数据（hadoop集群上部署的flume配置文件中，设置采集本地生产的数据文件）
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);
        //消费者关注topic
        consumer.subscribe(Arrays.asList(Names.TOPIC.getValue()));
        //创建HBaseDao对象
        HBaseDao hBaseDao = new HBaseDao();
        //初始化
        hBaseDao.init();
        //消费数据
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                //System.out.println(record.value());
                //存储数据
                hBaseDao.insertData(record.value());
            }
        }

    }

    @Override
    public void close() throws IOException {

    }
}
