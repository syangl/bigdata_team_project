package com.nd.sl.producer.bean;

import com.nd.sl.common.bean.DataIn;
import com.nd.sl.common.bean.DataOut;
import com.nd.sl.common.bean.Producer;
import com.nd.sl.common.util.DateUtil;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class LocalFileProducer implements Producer {
    private DataIn in;
    private DataIn in2;
    private DataOut out;
    private volatile boolean flag = true;//进程可共享

    @Override
    public void setIn(DataIn in, DataIn in2) {

        this.in = in;
        this.in2 = in2;
    }

    @Override
    public void setOut(DataOut out) {
        this.out = out;
    }

    @Override
    public void producer() throws IOException {
        try {
            List<user> users = in.read(user.class);
            List<goods> goods = in2.read(goods.class);
            while (flag) {
                //从用户表和商品表中随机生成用户和商品
                int customerindex = new Random().nextInt(users.size());//下标
                int goodsindex = -1;//商品下标
                user user1 = users.get(customerindex);
                goods goods1 = null;
                goodsindex = new Random().nextInt(goods.size());//商品下标
                goods1 = goods.get(goodsindex);

                //生产购买时间
                String startDate = "202101010000000";
                String endDate = "202201010000000";
                long startTime = DateUtil.parse(startDate, "yyyyMMddHHmmss").getTime();
                long endTime = DateUtil.parse(endDate, "yyyyMMddHHmmss").getTime();
                //购买时间
                long buyTime = startTime + (long) ((endTime - startTime) * Math.random());
                //把购买时间转换为字符串
                String buyTimeString = DateUtil.format(new Date(buyTime), "yyyyMMddHHmmss");
                String buytime = buyTimeString.substring(0, 8);

//            生成购买数目
                int d;
                while (true) {
                    d = (new Random().nextInt(6));
                    if (d != 0) {
                        break;
                    }
                }

                String number = String.valueOf(d);
                record records = new record(goods1.getGoodsid(), goods1.getGoodsname(), user1.getUseid(), goods1.getPrice(), number, buytime);

                //将销售记录刷新到数据文件中
                out.write(records);
                //设置每五秒
                System.out.println(records);
                Thread.sleep(500);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.close();
        }
    }
}
