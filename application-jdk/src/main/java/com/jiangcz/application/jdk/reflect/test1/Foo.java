package com.jiangcz.application.jdk.reflect.test1;

/**
 * 类名称：Foo<br>
 * 类描述：<br>
 * 创建时间：2018年08月25日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Foo {

    void algo1(){
        //algo1
    }

    void algo2(){
        //algo1
    }

    void algo3(){
        //algo1
    }

    public static void main(String[] args) {
        TimeUtil tu = new TimeUtil();
        tu.getTime(() -> new Foo().algo1());
        tu.getTime(() -> new Foo().algo2());
        tu.getTime(() -> new Foo().algo3());
    }
}
