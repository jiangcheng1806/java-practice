package com.jiangcz.application.jdk.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarTest {
    public static void main(String[] args) {
        //add与roll的区别
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca1 = Calendar.getInstance();//2019-11-21 16:24:31
        Calendar ca2 = Calendar.getInstance();//2019-11-21 16:24:31
        ca1.add(Calendar.MONTH,3);//add加3个月
        ca2.roll(Calendar.MONTH,3);//roll加3个月
        System.out.println(sf.format(ca1.getTime()));//进位：2020-02-21 16:24:31
        System.out.println(sf.format(ca2.getTime()));//不进位：2019-02-21 16:24:31

    }
}
