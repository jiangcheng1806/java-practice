package com.jiangc.practice.algorithm.bitwise;

/**
 * 类名称：XorOperation<br>
 * 类描述：<br>
 * 创建时间：2018年07月16日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class XorOperation {
    public static void main(String[] args) {
        int a = 15;
        System.out.println(" a 的二进制 是 ：" + Integer.toBinaryString(a));
        int b = 2;
        System.out.println(" b 的二进制是 : " + Integer.toBinaryString(b));

        System.out.println(" a 与 b 异或的结果是 :" + (a^b));

        System.out.println(" a 与 b 异或的结果二进制是 :" +Integer.toBinaryString(a^b));
    }
}
