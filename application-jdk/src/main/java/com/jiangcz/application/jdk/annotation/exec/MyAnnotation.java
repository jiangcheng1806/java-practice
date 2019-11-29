package com.jiangcz.application.jdk.annotation.exec;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jiangcheng
 * 注解类
 */
/*
RetetionPolicy.SOURSE：java源文件时期，如@Overried和@SuppressWarning
RetetionPolicy.CLASS： class文件时期（默认阶段）
RetetionPolicy.RUNTIME：运行时期，如@Deprecated
*/

@Retention(RetentionPolicy.RUNTIME)

/*
Target：用于说明注解类的使用范围。如在方法上还是类上，默认值是任何地方。
其值可设置为枚举类ElementType类中的任何一个，包括：包、字段、方法、方法参数、构造器、类等值。取值为：
PACKAGE(包声明)
FIELD(字段声明)
ANNOTATION_TYPE(注释类型声明)
CONSIRUCTOR(构造器声明)
METHOD(方法声明)
PARAMETER(参数声明)
TYPE(类、接口（包含注释类型）或枚举声明)
LOCAL_VARIABLE(局部变量声明)
*/

@Target({ElementType.METHOD, ElementType.TYPE})
public @interface MyAnnotation {

    String color() default "red";

    String value();

    //数组
    int[] arr() default {1, 2, 3};

    //枚举
    EnumTest.TrafficLamp lamp() default EnumTest.TrafficLamp.GREEN;

    //注解类
    MetaAnnotation annotation() default @MetaAnnotation("startup-jdk");

    //class类
    Class clazz() default System.class;

}
