package com.jiangcz.application.jdk.modifier;

/**
 * 类名称：Colleague<br>
 * 类描述：<br>
 * 创建时间：2019年04月04日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Colleague extends Person {

    //父类 的 final 修饰的方法不能覆盖， 但可以被继承

    public static void main(String[] args) {
         final int age = 1;
         //int age = 2;
        System.out.println(age);

        Colleague c = new Colleague();

        c.speak();
    }


}
