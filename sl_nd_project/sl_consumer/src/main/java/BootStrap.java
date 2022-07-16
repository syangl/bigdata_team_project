import com.nd.sl.common.bean.Consumer;
import com.nd.sl.consumer.bean.RecordConsumer;

import java.io.IOException;

/**
 * @ClassName: BootStrap
 * @PackageName:PACKAGE_NAME
 * @Description: 消费者启动类,消费kafka数据，存储到hbase
 * @Author LiuSiyang
 * @Date 2022/7/16 10:25
 * @Version 1.0.0
 */
public class BootStrap {
    public static void main(String[] args) throws IOException {
        //创建消费者
        Consumer consumer = new RecordConsumer();
        //消费数据
        consumer.consumer();
        //关闭资源
        consumer.close();
    }
}
