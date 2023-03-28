package com.jiangc.practice.algorithm.bitwise;

/**
 * 类名称：NonOperation<br>
 * 类描述：<br>
 * 创建时间：2018年07月16日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class NonOperation {
    public static void main(String[] args) {
        int a = 2;

        System.out.println("a 的二进制 ：" + Integer.toBinaryString(a));

        System.out.println(" a  非的结果是 :" + (~a));

        System.out.println(" ~a 的二进制是 :" + Integer.toBinaryString(~a));
    }
}
