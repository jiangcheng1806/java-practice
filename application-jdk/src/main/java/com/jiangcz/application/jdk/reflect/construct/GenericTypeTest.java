package com.jiangcz.application.jdk.reflect.construct;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 泛型测试
 */
public class GenericTypeTest {

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {

        Class<A> aClass = A.class;

        // 泛型方法返回类型
        Method method = aClass.getMethod("getArr");

        Type genericReturnType = method.getGenericReturnType();

        if (genericReturnType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) genericReturnType;
            Type[] actualTypeArguments = type.getActualTypeArguments();
            for (Type typeArgument : actualTypeArguments) {
                Class typeArgClass = (Class) typeArgument;
                System.out.println("泛型方法返回类型 = " + typeArgClass);
            }
        }

// 泛型方法参数类型
        method = aClass.getMethod("setArr", List.class);

        Type[] genericParameterTypes = method.getGenericParameterTypes();

        for (Type genericParameterType : genericParameterTypes) {
            if (genericParameterType instanceof ParameterizedType) {
                ParameterizedType aType = (ParameterizedType) genericParameterType;
                Type[] parameterArgTypes = aType.getActualTypeArguments();
                for (Type parameterArgType : parameterArgTypes) {
                    Class parameterArgClass = (Class) parameterArgType;
                    System.out.println("泛型方法参数类型 = " + parameterArgClass);
                }
            }
        }

        Field[] fields = aClass.getFields();

        /**
         * 一个都没有
         */
        for (Field field : fields){
            System.out.println(field.getName());
        }

// 泛型成员变量类型
        Field field = aClass.getDeclaredField("arr");

        Type genericFieldType = field.getGenericType();

        if (genericFieldType instanceof ParameterizedType) {
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Type[] fieldArgTypes = aType.getActualTypeArguments();
            for (Type fieldArgType : fieldArgTypes) {
                Class fieldArgClass = (Class) fieldArgType;
                System.out.println("泛型变量类型 = " + fieldArgClass);
            }
        }
    }

}
