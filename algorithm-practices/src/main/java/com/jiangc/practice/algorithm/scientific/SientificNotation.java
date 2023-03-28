package com.jiangc.practice.algorithm.scientific;

import static java.lang.Float.NaN;
import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 * 类名称：SientificNotation<br>
 * 类描述：<br>
 * 创建时间：2018年07月28日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class SientificNotation {

    public static void main(String[] args) {

        System.out.println("Math.sqrt(-1.0) = "+Math.sqrt(-10.0));//Math.sqrt(-1.0) = NaN

        System.out.println("0.0/0.0 = "+0.0/0.0);//0.0/0.0 = NaN

        System.out.println("1.0/0.0 = "+1.0/0.0);//1.0/0.0 = Infinity

        System.out.println("-1.0/0.0 = "+ -1.0/0.0);//-1.0/0.0 = -Infinity

        System.out.println("NaN + 1.0 = "+ (NaN + 1.0));//NaN + 1.0 = NaN

        System.out.println("Infinity + 1.0 = "+ (Infinity + 1.0));//Infinity + 1.0 = Infinity

        System.out.println("Infinity + Infinity = "+ (Infinity + Infinity));//Infinity + Infinity = Infinity

        System.out.println("NaN > 1.0 = "+ (NaN > 1.0));//NaN > 1.0 = false

        System.out.println("NaN == 1.0 = "+ (NaN == 1.0));//NaN == 1.0 = false

        System.out.println("NaN < 1.0 = "+ (NaN < 1.0));//NaN < 1.0 = false

        System.out.println("NaN == NaN = "+ (NaN == NaN));//NaN == NaN = false

        System.out.println("0.0 == -0.01 = "+ (0.0 == -0.01));//0.0 == -0.01 = false

        System.out.println("0.0 == -0.0 = "+ (0.0 == -0.0));//0.0 == -0.0 = true


        /*0.1
        0.2
        0.30000000000000004
        0.4
        0.5
        0.6
        0.7
        0.7999999999999999
        0.8999999999999999
        0.9999999999999999
        1.0999999999999999
        1.2
        1.3
        1.4000000000000001
        1.5000000000000002
        1.6000000000000003
        1.7000000000000004
        1.8000000000000005
        1.9000000000000006
        2.0000000000000004
        2.1000000000000005
        2.2000000000000006
        2.3000000000000007
        2.400000000000001
        2.500000000000001
        2.600000000000001*/
        double s = 0;
        for (int i = 0; i < 26; i++) {
            s += 0.1;
            System.out.println(s);
        }


        double d = 29.0 * 0.01;
        System.out.println(d);
        System.out.println((int)(d*100));

    }
}
