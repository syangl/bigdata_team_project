package com.nd.sl.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    //将日期转为字符串
    public  static String format(Date date,String format){
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        return sdf.format(date);
    }
    //将日期字符串按照指定格式解析为日期对象
    public static Date parse(String dataString,String format){
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        Date date=null;
        try {
            date=sdf.parse(dataString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date;
    }
}
