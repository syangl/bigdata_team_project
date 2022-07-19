package com.nd.service.impl;
/*
 * Created with IntelliJ IDEA.
 * ClassName: RecordServiceImpl
 * User: 123
 * Date: 2022/7/17
 * Time: 9:28
 * Description:通话日志业务层实现类
 */

import com.nd.dao.RecordDao;
import com.nd.entity.MonthRecord;
import com.nd.entity.ObjectRecord;
import com.nd.entity.Record;
import com.nd.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service//业务层
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordDao recordDao;

    @Override
    public List<Record> getRecord(String pName) {
        //集合
        Map<String,Object> map=new HashMap<>();
        map.put("pName",pName);
        List<Record> record = recordDao.getRecord(map);
        return record;
    }

    @Override
    public List<MonthRecord> getMonth() {
        List<Record> records=recordDao.getMonth();
        List<MonthRecord> list=new ArrayList<>();

        MonthRecord dm=null;
        int sum=0;
        int i=1;
        while (i<=12){
            for (Record record : records) {
                if(record.getMonth()==i){
                    sum+=record.getAmount();
                }
            }
            dm=new MonthRecord(i,sum);
            list.add(dm);
            i++;
            sum=0;
        }
        return list;
    }
    public List<String> getPName() throws ClassNotFoundException, SQLException {
        String DRIVER="com.mysql.jdbc.Driver";
        String URL="jdbc:mysql://hadoop101:3306/sl?useUnicode=true&characterEncoding=UTF-8&&serverTimezone=GMT%2B8";
        String USER="root";
        String PASSWORD="root";
        List<String> list=new ArrayList<>();
        //注册驱动
        Class.forName(DRIVER);
        //连接数据库
        Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
        //开始查询
        Statement statement = conn.createStatement();
        String sqlQuery="select pname from tb_goods";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        //保存数据
        while (resultSet.next()){
            list.add(resultSet.getString("pname"));
        }
        resultSet.close();
        statement.close();
        conn.close();
        return list;
    }
    @Override
    public List<ObjectRecord> getObject() throws SQLException, ClassNotFoundException {
        List<String> pname=getPName();
        List<Record> records=recordDao.getObject();
        List<ObjectRecord> list=new ArrayList<>();

        ObjectRecord ob=null;
        int sum=0;int i=1;
        while (i<=40){
            for (Record record : records) {
                if(record.getPname().equals(pname.get(i-1))){
                    sum+=record.getAmount();
                }
            }
            ob=new ObjectRecord(String.valueOf(i),pname.get(i-1),sum);
            list.add(ob);
            sum=0;
            i++;
        }
        return list;
    }

    @Override
    public List<Record> getpermonth(Integer pmonth) {
        Map<String,Object> map=new HashMap<>();
        map.put("pmonth",pmonth);
        List<Record> record = recordDao.getpermonth(map);
        return record;
    }
}
