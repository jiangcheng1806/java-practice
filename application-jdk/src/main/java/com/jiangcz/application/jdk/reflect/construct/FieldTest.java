package com.jiangcz.application.jdk.reflect.construct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * 字段测试
 */
public class FieldTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException {

        Logger logger = LoggerFactory.getLogger(FieldTest.class);

        Class<A> aClass = A.class;

        // 成员变量赋值
// 实例化一个对象
        A myObject = aClass.newInstance();
// 根据名称获取某个成员变量
        Field name = aClass.getDeclaredField("s");

// 因为name成员变量为private，所以必须设置可访问为true，不然直接赋值会报IllegalAccessException
        name.setAccessible(true);

// set方法需要两个参数，第一个为某个对象，第二个为需要设置的值
// 下面的方法可理解为：将上面实例化的对象myObject-->的成员变量name-->赋值为小黑
        name.set(myObject, "小黑");
        logger.debug("{}", myObject);
    }
}
