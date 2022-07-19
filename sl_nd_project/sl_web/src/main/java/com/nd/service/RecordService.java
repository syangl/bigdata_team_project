package com.nd.service;
/*
 * Created with IntelliJ IDEA.
 * ClassName: RecordService
 * User: 123
 * Date: 2022/7/17
 * Time: 9:27
 * Description:访问销售记录业务层接口
 */

import com.nd.entity.MonthRecord;
import com.nd.entity.ObjectRecord;
import com.nd.entity.Record;

import java.sql.SQLException;
import java.util.List;

public interface RecordService {
    //查询销售记录
    List<Record> getRecord(String pName);
    List<MonthRecord> getMonth();
    List<ObjectRecord> getObject() throws SQLException, ClassNotFoundException;
    List<Record> getpermonth(Integer pmonth);
}
