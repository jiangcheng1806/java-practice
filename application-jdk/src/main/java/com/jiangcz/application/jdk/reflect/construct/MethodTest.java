package com.jiangcz.application.jdk.reflect.construct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 方法测试
 */
public class MethodTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Logger logger = LoggerFactory.getLogger(MethodTest.class);

        Class<A> aClass = A.class;
        // 获取方法（只能获取public的），包括继承来的
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            logger.debug("public方法 {}", method);
        }

        // 获取所有的方法（包括public, protected, default (package) access, 以及 private 的）,但是不包括继承来的
        methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            logger.debug("所有的方法 {}", method);
        }

// 实例化一个对象
        A myObject = aClass.newInstance();
        Method setNameMethod = aClass.getMethod("setS",String.class);

        // 获取返回类型
        Class<?> returnType = setNameMethod.getReturnType();
        logger.debug("setNameMethod返回类型 {}", returnType);

        // 获取方法参数
        Class<?>[] parameterTypes = setNameMethod.getParameterTypes();
        for (Class<?> parameterType : parameterTypes) {
            logger.debug("setNameMethod方法参数 {}", parameterType);
        }

// 调用方法，第一个参数为某个对象，第二个参数为需要设置的值，返回值为当前方法的返回值
// 下面的方法可理解为：调用上面实例化的对象myObject-->的setName方法-->传入的参数为“小花”
        Object setNameMethodResult = setNameMethod.invoke(myObject, "小花");

        logger.debug("myObject {}", myObject);

// 因为setName方法没有返回值，所以setNameMethodResult为null
        logger.debug("setNameMethodResult {}", setNameMethodResult);

        Method getNameMethod = aClass.getMethod("getS");

        Object getNameMethodResult = getNameMethod.invoke(myObject);
// getName的方法的返回值为刚才通过setName方法设置的“小花”
        logger.debug("getNameMethodResult {}", getNameMethodResult);

    }
}
