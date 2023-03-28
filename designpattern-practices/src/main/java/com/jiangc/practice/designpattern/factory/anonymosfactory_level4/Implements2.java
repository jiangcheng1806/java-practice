package com.jiangc.practice.designpattern.factory.anonymosfactory_level4;

/**
 * 类名称：Implements2<br>
 * 类描述：<br>
 * 创建时间：2018年08月17日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Implements2 implements Service {
    @Override
    public void method1() {
        System.out.println("Implements2.method1");
    }

    @Override
    public void method2() {
        System.out.println("Implements2.method2");
    }

    public static ServiceFactory Implements2Factory = () -> new Implements2();
}
