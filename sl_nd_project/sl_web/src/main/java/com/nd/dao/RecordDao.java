package com.nd.dao;
/*
 * Created with IntelliJ IDEA.
 * InterfaceName: RecordDao
 * User: 123
 * Date: 2022/7/17
 * Time: 9:25
 * Description:访问销售记录数据持久层
 */

import com.nd.entity.MonthRecord;
import com.nd.entity.ObjectRecord;
import com.nd.entity.Record;

import java.util.List;
import java.util.Map;

public interface RecordDao {
    //查询某件商品销售情况
    List<Record> getRecord(Map<String,Object> map);
    List<Record> getMonth();
    List<Record> getObject();
}
