package com.jiangcz.application.jdk.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarSetTest {
    public static void main(String[] args) {
        //set:设置日历字段的值
        System.out.println("#################### set #####################");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2018);
        System.out.println("修改年份后："+calendar.get(Calendar.YEAR));
        calendar.set(2018, Calendar.MAY,13,15,1,11);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
    }
}
