package com.nd.sl.consumer.dao;

import com.nd.sl.common.bean.BaseHBaseDao;
import com.nd.sl.common.constant.Names;
import com.nd.sl.common.constant.ValueConstant;
import org.apache.hadoop.hbase.client.Put;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName: HBaseDao
 * @PackageName:com.nd.sl.consumer.dao
 * @Description: HBase访问
 * @Author LiuSiyang
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

    }

    /**
     * put多条数据
     * @param name
     * @param puts
     */
    protected void putData(String name, List<Put> puts) throws IOException {

    }
}
