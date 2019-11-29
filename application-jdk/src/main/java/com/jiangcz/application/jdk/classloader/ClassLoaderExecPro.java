package com.jiangcz.application.jdk.classloader;

import java.util.Date;

public class ClassLoaderExecPro {

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //将用自定义的类加载器加载.class文件
        Class clazz = new MyClassLoader("startup-jdk").loadClass("com.jiangcz.application.jdk.classloader.ClassLoaderAttachment");
        Date d1 = (Date) clazz.newInstance();//获取class类的实例对象
        System.out.println(d1);
    }
}
