package com.jiangcz.application.jdk.string;

import java.math.BigDecimal;

public class DigitTest {
    public static void main(String[] args) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        String s = "30.000000000";
        BigDecimal bd = new BigDecimal(s);
        Integer i = bd.intValue();
        System.out.println(i);
    }
}
