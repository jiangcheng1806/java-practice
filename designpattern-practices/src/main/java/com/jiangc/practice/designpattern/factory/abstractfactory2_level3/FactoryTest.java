package com.jiangc.practice.designpattern.factory.abstractfactory2_level3;

/**
 * 类名称：FactoryTest<br>
 * 类描述：其实也是一个抽象工厂<br>
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
        serviceConsumer(new Implements1Factory());
        serviceConsumer(new Implements2Factory());
    }
}
