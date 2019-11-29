package com.jiangcz.application.jdk.reflect.test2;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 类名称：ReflectTest<br>
 * 类描述：<br>
 * 创建时间：2019年07月12日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class ReflectTest {
    public static void main(String[] args) throws Exception {
        Person person = new Person("jack", 25);

        // test get private value
        System.out.println("jack's name:" + ReflectionUtil.getValue(person, "name"));
        System.out.println("jack's age:" + ReflectionUtil.getValue(person, "age"));

        // test set private value
        ReflectionUtil.setValue(person, "name", "jason");
        ReflectionUtil.setValue(person, "age", 10);
        System.out.println("jack's name:" + ReflectionUtil.getValue(person, "name"));
        System.out.println("jack's age:" + ReflectionUtil.getValue(person, "age"));

        // test call private method
        String result = (String) ReflectionUtil.callMethod(person, "getInfo", new Class[] { String.class, int.class },
                new Object[] { "I hava ", 4 });
        System.out.println("result: " + result);


        Field[] fields = person.getClass().getFields();
        System.out.println(fields.length);

        Field[] fields1 = person.getClass().getDeclaredFields();
        System.out.println(fields1.length);

        for (Field field : fields1) {
            System.out.println(field.getName());
            if (field.getName().equals("name")){
                field.setAccessible(true);
                field.set(person,"aaaaa");
                field.setAccessible(false);
            }
            System.out.println(ToStringBuilder.reflectionToString(person));
        }

        String[] pars1 = new String[]{"createTime","createBy"};
        String[] pars2 = new String[]{"updateTime","updateBy"};

        System.out.println(ArrayUtils.contains(pars1,pars2));
        System.out.println(Objects.toString(pars1) + ToStringBuilder.reflectionToString(pars1)) ;
        System.out.println(Objects.toString(pars2) + ToStringBuilder.reflectionToString(pars2));

        List<String> list = Arrays.asList("createTime","createBy");
        System.out.println(list);
    }
}
