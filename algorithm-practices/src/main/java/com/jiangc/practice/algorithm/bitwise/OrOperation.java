<<<<<<< HEAD:algorithm-practices/src/main/java/com/jiangc/practice/algorithm/bitwise/OrOperation.java
package com.jiangc.practice.algorithm.bitwise;
=======
package com.jiangcz.application.algorithm.bitwise;
>>>>>>> adc6b5b733e418e2a2143a68eda74f9d9a3ab232:application-algorithm/src/main/java/com/jiangcz/application/algorithm/bitwise/OrOperation.java

/**
 * 类名称：OrOperation<br>
 * 类描述：<br>
 * 创建时间：2018年07月16日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class OrOperation {
    public static void main(String[] args) {
        int a = 129;
        int b = 128;

        System.out.println(" a 的二进制形态是 " + Integer.toBinaryString(a));
        System.out.println(" b 的二进制形态是 " + Integer.toBinaryString(b));
        System.out.println("a or b result is : " + (a|b));
        System.out.println(" a 或 b 的二进制形态是 : " + Integer.toBinaryString(a|b));

    }
}
