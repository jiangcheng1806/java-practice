package com.jiangcz.application.designpattern.ioc;

import org.junit.Test;

/**
 * 类名称：SimpleIocTest<br>
 * 类描述：<br>
 * 创建时间：2019年09月16日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class SimpleIocTest {
    @Test
    public void getBean() throws Exception {
        String location = SimpleIoc.class.getClassLoader().getResource("spring-test.xml").getFile();
        SimpleIoc bf = new SimpleIoc(location);
        Wheel wheel = (Wheel) bf.getBean("wheel");
        System.out.println(wheel);
        Car car = (Car) bf.getBean("car");
        System.out.println(car);
    }
}
