package com.jiangcz.application.jdk.reflect.construct;

import java.lang.annotation.Annotation;

/**
 * 注解测试
 */
public class AnnotationTest {
    public static void main(String[] args) {
        Class<A> aClass = A.class;
// 获取所有的注解
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

// 获取指定的注解
        MyAnnotation myAnnotation = aClass.getAnnotation(MyAnnotation.class);
        String name = myAnnotation.name();
        String value = myAnnotation.value();
        System.out.println(name);
        System.out.println(value);
    }
}
