package com.jiangcz.application.jdk.time;

import java.util.Calendar;

public class CalendarGetTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        //get:返回指定日历字段的值
        System.out.println("#################### get #####################");
        System.out.println("当前日期:"+calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)
                +"-"+calendar.get(Calendar.DATE));
        System.out.println("当前时间:"+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)
                +":"+calendar.get(Calendar.SECOND)+":"+calendar.get(Calendar.MILLISECOND));
        System.out.println("12小时制:"+calendar.get(Calendar.HOUR));
        System.out.println("24小时制:"+calendar.get(Calendar.HOUR_OF_DAY));
    }
}
