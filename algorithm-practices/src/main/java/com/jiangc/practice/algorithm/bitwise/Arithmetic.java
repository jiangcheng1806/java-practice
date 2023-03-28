package com.jiangc.practice.algorithm.bitwise;

/**
 * 类名称：Arithmetic<br>
 * 类描述：<br>
 * 创建时间：2018年07月16日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Arithmetic {

    public static void main(String[] args) {

        int a = 22;
        int b = 5;
        double c = 5;

        System.out.println( b + "+ " + c + "=" + (b+c));
        System.out.println( b + "- " + c + "=" + (b-c));
        System.out.println( b + "* " + c + "=" + (b*c));
        System.out.println( a + "/ " + b + "=" + (a/b));
        System.out.println( a + "% " + b + "=" + (a%b));
        System.out.println( a + "/ " + c + "=" + (a/c));
        System.out.println( a + "% " + c + "=" + (a%c));
    }

}
