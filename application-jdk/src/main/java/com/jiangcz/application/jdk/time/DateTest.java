package com.jiangcz.application.jdk.time;

import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        Date date1  = new Date();
        Date date2 = new Date(System.currentTimeMillis()+1000);
        System.out.println("date1: "+date1);//date1: Thu Nov 21 13:54:52 CST 2019
        System.out.println("date2: "+date2);//date2: Thu Nov 21 13:54:53 CST 2019
        System.out.println("date1.compareTo(date2): "+date1.compareTo(date2));//-1
        System.out.println("date1.after(date2): "+date1.after(date2));//false
        System.out.println("date1.before(date2): "+date1.before(date2));//true
        System.out.println("date1.getTime(): "+date1.getTime());//1574315692607
        date1.setTime(System.currentTimeMillis());
        System.out.println("setTime() ==> date1.getTime(): "+date1.getTime());//1574315692628
    }
}
