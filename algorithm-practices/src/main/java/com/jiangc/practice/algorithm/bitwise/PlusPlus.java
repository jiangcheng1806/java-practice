<<<<<<< HEAD:algorithm-practices/src/main/java/com/jiangc/practice/algorithm/bitwise/PlusPlus.java
package com.jiangc.practice.algorithm.bitwise;
=======
package com.jiangcz.application.algorithm.bitwise;
>>>>>>> adc6b5b733e418e2a2143a68eda74f9d9a3ab232:application-algorithm/src/main/java/com/jiangcz/application/algorithm/bitwise/PlusPlus.java

/**
 * 类名称：PlusPlus<br>
 * 类描述：<br>
 * 创建时间：2018年07月16日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class PlusPlus {
    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        int sum = a+ ++b;
        System.out.println("a=" + a + ",b="+b+",sum="+sum);



        int a1 = 10;
        int b1 = 10;
        int sum1 = a1+++b1;//相当于a1++ +b1
        System.out.println("a1 = " + a1 + ",b="+b1+",sum1 =" + sum1);
    }
}
