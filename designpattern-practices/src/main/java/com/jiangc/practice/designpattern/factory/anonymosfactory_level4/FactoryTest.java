package com.jiangc.practice.designpattern.factory.anonymosfactory_level4;

/**
 * 类名称：FactoryTest<br>
 * 类描述：<br>
 * 创建时间：2018年08月17日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class FactoryTest {
    public static void serviceConsumer(ServiceFactory factory){
        Service s = factory.getService();
        s.method1();
        s.method2();
    }

    public static void main(String[] args) {
        serviceConsumer(Implements1.Implements1Facory);
        serviceConsumer(Implements2.Implements2Factory);
    }
}
