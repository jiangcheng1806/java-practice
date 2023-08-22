package com.jiangcz.application.jdk.time;

import java.util.Calendar;

public class CalendarAddTest {
    public static void main(String[] args) {

//add:基于日历规则实现日期加减
        System.out.println("#################### add #####################");
        Calendar cal = Calendar.getInstance();
        System.out.println("当前月份："+(cal.get(Calendar.MONTH)+1));
        cal.add(Calendar.MONTH,2);
        System.out.println("加上2个月："+(cal.get(Calendar.MONTH)+1));
        cal.add(Calendar.MONTH,-2);
        System.out.println("再加上-2个月："+(cal.get(Calendar.MONTH)+1));
    }
}
