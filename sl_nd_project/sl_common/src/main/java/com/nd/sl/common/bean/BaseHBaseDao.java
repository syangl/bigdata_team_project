package com.nd.sl.common.bean;

import com.nd.sl.common.constant.Names;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * @ClassName: BaseHBaseDao
 * @PackageName:com.nd.sl.common.bean
 * @Description: 基础数据访问对象
 * @Author LiuSiyang
 * @Date 2022/7/16 11:14
 * @Version 1.0.0
 */
public class BaseHBaseDao {
    /**
     * 创建线程访问对象
     */
    private ThreadLocal<Connection> connectHolder =new ThreadLocal<>();
    private ThreadLocal<Admin> adminHolder=new ThreadLocal<>();

    /**
     * 开始连接
     * @throws IOException
     */
    protected void start() throws IOException {
        //获取连接
        getConnect();
        //获取admin
        getAdmin();
    }

    /**
     * 结束，释放资源
     * @throws IOException
     */
    protected void end() throws IOException {
        Admin admin = getAdmin();
        if(admin!=null){
            admin.close();
            adminHolder.remove();
        }
        Connection conn = getConnect();
        if(conn!=null){
            conn.close();
            connectHolder.remove();
        }
    }

    /**
     * 获取admin
     * @return
     * @throws IOException
     */
    protected synchronized Admin getAdmin() throws IOException {
        Admin admin = adminHolder.get();
        if (admin == null) {
            admin = getConnect().getAdmin();
            adminHolder.set(admin);
        }
        return admin;
    }

    /**
     * 同步获取连接对象
     * @return
     * @throws IOException
     */
    protected synchronized Connection getConnect() throws IOException {
        //获取连接对象
        Connection connection = connectHolder.get();
        if (connection == null) {
            Configuration conf = HBaseConfiguration.create();
            connection = ConnectionFactory.createConnection(conf);
            connectHolder.set(connection);
        }
        return connection;
    }

    /**
     * 创建命名空间
     * @param name 命名空间名
     */
    protected void createNamespace(String name) throws IOException {
        Admin admin = getAdmin();
        try {
            //获取命名空间描述器,没有就创建
            admin.getNamespaceDescriptor(name);
        }catch (NamespaceNotFoundException e){
            //创建命名空间描述器
            NamespaceDescriptor namespaceDescriptor =
                    NamespaceDescriptor.create(name).build();
            //创建命名空间
            admin.createNamespace(namespaceDescriptor);
        }
    }

    /**
     * 创建无预分区表
     * @param name 表名
     * @param families 列族名
     */
    protected void createTableTX(String name,String...families) throws IOException {
        createTableTX(name,null,families);
    }
    /**
     * 创建有预分区的表
     * @param name 表名
     * @param regionNum 分区号
     * @param families 列族名
     */
    protected void createTableTX(String name,Integer regionNum,String... families) throws IOException {
        Admin admin=getAdmin();
        TableName tableName = TableName.valueOf(name);
        if (admin.tableExists(tableName)) {
            //表存在，删除表
            deleteTable(name);
        }
        //创建表
        createTable(name,regionNum,families);
    }

    /**
     * 创建表
     * @param name 表名
     * @param regionNum 分区号
     * @param families 列族名
     */
    protected void createTable(String name, Integer regionNum, String... families) throws IOException {
        Admin admin=getAdmin();
        TableName tableName = TableName.valueOf(name);
        //创建表的描述器
        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
        //列族
        if(families.length==0||families==null){
            families=new String[1];
            families[0]= Names.CF_INFO.getValue();
        }
        for (String family : families) {
            //添加列的描述器
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(family);
            hTableDescriptor.addFamily(hColumnDescriptor);
        }
        //增加预分区
        if (regionNum == null || regionNum <= 0) {
            //没有分区键
            admin.createTable(hTableDescriptor);
        } else {
            //有分区键
            byte[][] splitKeys = genSplitKeys(regionNum);
            admin.createTable(hTableDescriptor, splitKeys);
        }
    }

    /**
     * 删除表
     * @param name
     * @throws IOException
     */
    protected void deleteTable(String name) throws IOException {
        TableName tableName = TableName.valueOf(name);
        Admin admin=getAdmin();
        //关闭表
        admin.disableTable(tableName);
        //删除表
        admin.deleteTable(tableName);
    }


    /**
     * 生成分区键
     * @param regionNum 分区数量
     * @return
     */
    private byte[][] genSplitKeys(int regionNum) {

    }

    /**
     * 生成分区号插入数据
     * @param cid 消费者id 1234567890
     * @param date 购买日期 20210105
     * @return
     */
    protected int genRegionNum(String cid,String date) {

    }



}

