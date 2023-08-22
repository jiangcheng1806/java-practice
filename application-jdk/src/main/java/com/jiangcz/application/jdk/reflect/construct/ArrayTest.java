package com.jiangcz.application.jdk.reflect.construct;

import java.lang.reflect.Array;

public class ArrayTest {
    public static void main(String[] args) throws ClassNotFoundException {
        // 创建一个数组
        int[] intArray = (int[]) Array.newInstance(int.class, 3);

// 为数组赋值
        Array.set(intArray, 0, 123);
        Array.set(intArray, 1, 456);
        Array.set(intArray, 2, 789);

// 访问一个数组
        System.out.println("intArray[0] = " + Array.get(intArray, 0));
        System.out.println("intArray[1] = " + Array.get(intArray, 1));
        System.out.println("intArray[2] = " + Array.get(intArray, 2));

// 获取数组的Class对象
        Class stringArrayClass = String[].class;
        System.out.println("stringArrayClass = " + stringArrayClass);


// 获得一个原生数据类型（primitive）int数组的Class对象
// 在JVM中字母I代表int类型，左边的‘[’代表我想要的是一个int类型的数组，这个规则同样适用于其他的原生数据类型
        Class intArrayClass = Class.forName("[I");
        System.out.println("intArrayClass = " + intArrayClass);


// 普通对象类型的数组,注意‘[L’的右边是类名
// 类名的右边是一个‘;’符号
        Class stringArrayClass2 = Class.forName("[Ljava.lang.String;");
        System.out.println("stringArrayClass2 = " + stringArrayClass2);


// 一旦你获取了类型的Class对象，你就有办法轻松的获取到它的数组的Class对象，
// 首先你可以通过指定的类型创建一个空的数组，
// 然后通过这个空的数组来获取数组的Class对象。
        Class stringArrayClass3 = Array.newInstance(String.class, 0).getClass();
        System.out.println("stringArrayClass3 is array: " + stringArrayClass.isArray());


// 获取数组的成员类型
        String[] strings = new String[3];
        Class stringArrayClass4 = strings.getClass();
        Class stringArrayComponentType = stringArrayClass4.getComponentType();
        System.out.println("stringArrayComponentType = " + stringArrayComponentType);
    }
}
