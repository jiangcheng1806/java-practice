<<<<<<< HEAD:algorithm-practices/src/main/java/com/jiangc/practice/algorithm/bitwise/LogicalOperation.java
package com.jiangc.practice.algorithm.bitwise;
=======
package com.jiangcz.application.algorithm.bitwise;
>>>>>>> adc6b5b733e418e2a2143a68eda74f9d9a3ab232:application-algorithm/src/main/java/com/jiangcz/application/algorithm/bitwise/LogicalOperation.java

/**
 * 类名称：LogicalOperation<br>
 * 类描述：<br>
 * 创建时间：2018年07月16日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class LogicalOperation {
    public static void main(String[] args) {


        int a = 15;
        int b = 2;

        System.out.println(a + "&" + b + "=" + (a & b));
        System.out.println(a + "|" + b + "=" + (a | b));
        System.out.println(a + "^" + b + "=" + (a ^ b));


        int sum = 90;
        //String str = sum < 100 ? "失败" : "成功";

        String str = null;
        if (sum < 100) {
            str = "失败";
        } else {
            str = "成功";
        }

    }

}
