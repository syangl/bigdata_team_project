package com.nd.sl.common.bean;

import com.nd.sl.common.constant.Names;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.nd.sl.common.constant.ValueConstant;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * @ClassName: BaseHBaseDao
 * @PackageName:com.nd.sl.common.bean
 * @Description: 基础数据访问对象（Dao用于关系型数据库访问，有接口、实现类、存放传输数据的实体类）
 * @Author LiuSiyang&&Chendu
 * @Date 2022/7/16 11:14
 * @Version 1.0.0
 */
/**
 *Dao通俗来讲，就是将数据库操作都封装起来
 * */
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
     * 创建有预分区的表（分区即hbase table中的region）
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
            // 自定义的默认名称常量
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
     * 生成分区键（每个region是按照startRowKey和endRowKey划分的）
     * @param regionNum 分区数量
     * @return
     */
    private byte[][] genSplitKeys(int regionNum) {
        int splitKeyCount=regionNum-1;//如果有5个分区，只有4个分区键
        byte[][] bs=new byte[splitKeyCount][];//写一个二维数组,为分区键
        //分区键:0|,1|,2|,3|
        //[负无穷,0|),[0|,1|),[1|,2|),[2|,3|),[3|,正无穷)
        List<byte[]> bsList=new ArrayList<byte[]>();
        for (int i = 0; i < splitKeyCount; i++) {
            String splitKey=i+"|";
            System.out.println(splitKey);
            bsList.add(Bytes.toBytes(splitKey));
        }
        bsList.toArray(bs);
        return bs;
    }

    /**
     * 生成分区号插入数据
     * @param cid 消费者id 1234567890
     * @param date 购买日期 20210105
     * @return
     */
    protected int genRegionNum(String cid, String date) {
        //1234567890,取后四位没有规律的
        String userCode=cid.substring(cid.length()-4);
        //20210105,获取年月
        String yearMonth=date.substring(0,6);
        //实现散列（通过对象的内部地址(也就是物理地址)转换成一个整数，然后该整数通过hash函数的算法就得到了hashcode。
        // 所以，hashcode就是在hash表中对应的位置。）
        int userCodeHash=userCode.hashCode();
        int yearMonthHash=yearMonth.hashCode();
        //crc校验采用异或算法
        int crc=Math.abs(userCodeHash^yearMonthHash);
        //取模
        int num=crc% ValueConstant.REGION_NUM;
        return num;
    }

    /**
     * put一条数据
     * @param name
     * @param put
     */
    /*
    * Put类中主要含有一个KeyValue对象数组，一个Put对象值代表一行数据；
    * 在KeyValue对象中，Key（键）包含了一个value值的row、family、column和timestamp信息，而value则是该表单元格的数据。
    *
    * */
    protected void putData(String name, Put put) throws IOException {
        Connection connection = getConnect();
        Table table = connection.getTable(TableName.valueOf(name));
        table.put(put);
        table.close();
    }

}

