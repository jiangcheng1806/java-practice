package com.jiangcz.application.jdk.classloader;

public class ClassLoaderExec {
    public static void main(String[] args) {
        /*
         * 用eclipse的打包工具将ClassLoaderExec输出成jre/lib/ext目录下的startup-jdk.jar包
         * 此时再在eclipse中运行这个类时，下面代码的while循环内的运行结果显示为ExtClassLoadr。
         * 这就表示，AppClassLoader在加载这个类时，会先委托给其上一级ExtClassLoader加载器去加载，而上级又委托上级
         * 但是ExtClassloader的上级没有找到要加载的类，就回到ExtClassLoader，此时它在jre/lib/ext中找到了，所以就结果就显示它了。
         * */
        ClassLoader loader = ClassLoaderExec.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.getClass().getName());
            loader = loader.getParent();//将此loader的上级赋给loader
        }

        System.out.println(loader);
    }
}
