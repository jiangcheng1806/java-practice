package com.jiangcz.application.jdk.reflect.test2;

/**
 * 类名称：Person<br>
 * 类描述：<br>
 * 创建时间：2019年07月12日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String getInfo(String str, int num) {
        return str + num + " apples";
    }
}
