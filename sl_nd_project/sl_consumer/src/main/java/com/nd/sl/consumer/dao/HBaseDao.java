package com.nd.sl.consumer.dao;

import com.nd.sl.common.bean.BaseHBaseDao;
import com.nd.sl.common.constant.Names;
import com.nd.sl.common.constant.ValueConstant;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @ClassName: HBaseDao
 * @PackageName:com.nd.sl.consumer.dao
 * @Description: HBase访问
 * @Author LiuSiyang&&Chendu
 * @Date 2022/7/16 11:45
 * @Version 1.0.0
 */
public class HBaseDao extends BaseHBaseDao {
    /**
     * 初始化
     * @throws IOException
     */
    public void init() throws IOException {
        start();
        //创建命名空间
        createNamespace(Names.NAMESPACE.getValue());
        //创建表
        createTableTX(
                Names.TABLE.getValue(),
                ValueConstant.REGION_NUM,
                Names.CF_INFO.getValue()
        );
        end();
    }

    /**
     * 插入数据
     * @param data
     * @throws IOException
     */
    public void insertData(String data) throws IOException {
        //将销售日志保存到HBase表中
        //1.获取销售日志数据
        String[] values = data.split("\t");
        String pid=values[0];
        String name=values[1];
        String cid=values[2];
        String unitprice=values[3];
        String num = values[4];
        String date = values[5];
        //2.创建数据对象
        //rowKey=regionNum+pid+cid+date
        String rowKey=genRegionNum(cid,date)+"_"+pid+"_"+cid+"_"+date;
        Put put=new Put(Bytes.toBytes(rowKey));
        byte[] family=Bytes.toBytes(Names.CF_INFO.getValue());
        //增加列
        put.addColumn(family,Bytes.toBytes("pid"),Bytes.toBytes(pid));
        put.addColumn(family,Bytes.toBytes("name"),Bytes.toBytes(name));
        put.addColumn(family,Bytes.toBytes("cid"),Bytes.toBytes(cid));
        put.addColumn(family,Bytes.toBytes("unitprice"),Bytes.toBytes(unitprice));
        put.addColumn(family,Bytes.toBytes("num"),Bytes.toBytes(num));
        put.addColumn(family,Bytes.toBytes("date"),Bytes.toBytes(date));
        //3.保存数据
        putData(Names.TABLE.getValue(),put);

    }

    /**暂时不需要
     * put多条数据
     * @param name
     * @param puts
     */
//    protected void putData(String name, List<Put> puts) throws IOException {
//        //获取表对象
//        Connection conn=getConnect();
//        Table table = conn.getTable(TableName.valueOf(name));
//        //增加数据
//        table.put(puts);
//        //关闭表
//        table.close();
//    }

}
