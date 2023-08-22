package com.jiangcz.application.jdk.annotation.simpleorm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解 完成表和类的映射
 *
 */
@Retention(RetentionPolicy.RUNTIME)  //因为要使用到反射,故注解信息必须保留到运行时
@Target(ElementType.TYPE) //只能用在类上
public @interface MyTable {
    //表名
    String value();
}
