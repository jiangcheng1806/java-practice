package com.jiangcz.application.jdk.classloader;

import java.util.Date;

//定义一个测试类，继承Date，便于使用时加载
public class ClassLoaderAttachment extends Date {

    //复写toString方法
    public String toString() {
        return "Hello World!";
    }
}
