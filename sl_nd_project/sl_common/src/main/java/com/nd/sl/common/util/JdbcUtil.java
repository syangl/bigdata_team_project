package com.nd.sl.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
    private static final String driverManager="com.mysql.jdbc.Driver";
    private static final String url="jdbc:mysql://hadoop101:3306/sl?useUnicode=true&characterEncoding=UTF-8&&serverTimezone=GMT%2B8";
    private static final String username="root";
    private static final String password="root";

    //获取连接
    public static Connection getConnection(){
        Connection conn=null;
        try {
            Class.forName(driverManager);
            conn= DriverManager.getConnection(url,username,password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
