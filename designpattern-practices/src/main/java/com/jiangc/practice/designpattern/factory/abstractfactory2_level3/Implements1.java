package com.jiangc.practice.designpattern.factory.abstractfactory2_level3;

/**
 * 类名称：Implements1<br>
 * 类描述：<br>
 * 创建时间：2018年08月17日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Implements1 implements Service {
    @Override
    public void method1() {
        System.out.println("Implements1.method1");
    }

    @Override
    public void method2() {
        System.out.println("Implements1.method2");
    }
}
