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
        //创建配置对象
        Properties prop=new Properties();
        prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("consumer.properties"));
        //获取flume采集的数据
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);
        //关注主题
        consumer.subscribe(Arrays.asList(Names.TOPIC.getValue()));
        //创建HBaseDao对象
        HBaseDao hBaseDao = new HBaseDao();
        //初始化
        hBaseDao.init();
        //消费数据
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
                //存储数据
                hBaseDao.insertData(record.value());
            }
        }

    }

    @Override
    public void close() throws IOException {

    }
}
