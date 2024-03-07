package com.nd.sl.producer;



import com.nd.sl.common.bean.Producer;
import com.nd.sl.producer.bean.LocalFileProducer;
import com.nd.sl.producer.io.LocalFileDataIn;
import com.nd.sl.producer.io.LocalFileDataOut;

import java.io.IOException;

public class Bootstrap {
    public static void main(String[] args) throws IOException {
        if(args.length<=0){
            System.out.println("系统参数不正确，请按照格式传参：java -jar producer.jar user.log goods.log record.log");
            System.exit(1);
        }

        //构建生产者对象
        Producer producer=new LocalFileProducer();
        producer.setIn(new LocalFileDataIn(args[0]),new LocalFileDataIn(args[1]));
        producer.setOut(new LocalFileDataOut(args[2]));
        //生产数据
        producer.producer();
        //释放资源
        producer.close();
    }

}
