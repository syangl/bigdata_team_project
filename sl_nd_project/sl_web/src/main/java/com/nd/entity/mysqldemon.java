package com.nd.entity;
/*
 * Created with IntelliJ IDEA.
 * ClassName: mysqldemon
 * User: 123
 * Date: 2022/7/17
 * Time: 23:19
 * Description:获取mysql数据库中存的商品名称
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class mysqldemon {
    static final String DRIVER="com.mysql.jdbc.Driver";
    static final String URL="jdbc:mysql://hadoop101:3306/sl?useUnicode=true&characterEncoding=UTF-8&&serverTimezone=GMT%2B8";
    static final String USER="root";
    static final String PASSWORD="root";

    public List<String> getPName() throws ClassNotFoundException, SQLException {
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

}
