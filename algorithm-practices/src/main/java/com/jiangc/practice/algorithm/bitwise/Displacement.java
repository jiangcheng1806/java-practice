<<<<<<< HEAD:algorithm-practices/src/main/java/com/jiangc/practice/algorithm/bitwise/Displacement.java
package com.jiangc.practice.algorithm.bitwise;
=======
package com.jiangcz.application.algorithm.bitwise;
>>>>>>> adc6b5b733e418e2a2143a68eda74f9d9a3ab232:application-algorithm/src/main/java/com/jiangcz/application/algorithm/bitwise/Displacement.java

/**
 * 类名称：Displacement<br>
 * 类描述：<br>
 * 创建时间：2018年07月16日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Displacement {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(6297));
        System.out.println(Integer.toBinaryString(-6297));

        System.out.println(Integer.toBinaryString(6297>>5));//右移左边补0
        System.out.println(Integer.toBinaryString(-6297>>5));//右移左边补1
        System.out.println(Integer.toBinaryString(6297>>>5));//右移左边补0 无符号右移
        System.out.println(Integer.toBinaryString(-6297>>>5));//右移左边补0 无符号右移
        System.out.println(Integer.toBinaryString(6297<<5));//左移右边补0
        System.out.println(Integer.toBinaryString(-6297<<5));//左移右边补0
    }
}
