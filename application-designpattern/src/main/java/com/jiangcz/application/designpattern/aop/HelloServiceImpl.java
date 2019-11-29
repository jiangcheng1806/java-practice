package com.jiangcz.application.designpattern.aop;

/**
 * 类名称：HelloServiceImpl<br>
 * 类描述：<br>
 * 创建时间：2019年09月16日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHelloWorld() {
        System.out.println("hello world!");
    }
}
