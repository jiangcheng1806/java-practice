package com.jiangcz.application.jdk.lambda;

import org.junit.Test;

/**
 * 类名称：LambdaInterface<br>
 * 类描述：关于使用接口并实现简单四则运算<br>
 * 创建时间：2018年06月09日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class LambdaInterface {
    //所需接口
    interface MathOperation {
        int operate(int a,int b);
    }

    @Test
    public void testPlus(){
        //不用类型声明
        MathOperation plus = (a, b) -> a + b;
        System.out.println(plus.operate(1,2));
    }

    @Test
    public void testSub(){
        //类型声明,没有大括号以及返回语句
        MathOperation sub = (int a, int b) -> a - b;
        System.out.println(sub.operate(2,1));
    }

    @Test
    public void testMulti(){
        //用大括号及返回语句
        MathOperation multi = (int a, int b) -> a * b;
        System.out.println(multi.operate(1,2));
    }
}
