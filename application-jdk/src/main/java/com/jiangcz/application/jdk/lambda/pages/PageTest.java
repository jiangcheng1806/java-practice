package com.jiangcz.application.jdk.lambda.pages;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类名称：PageTest<br>
 * 类描述：<br>
 * 创建时间：2019年09月09日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class PageTest {
    public static void main(String[] args) {


        //Stream.iterate(0,n -> n + 2).limit(5).forEach(System.out::println);
        //List<Double> list = Stream.generate(Math::random).limit(5).collect(Collectors.toList());
        //list.stream().forEach(System.out::println);

        //Stream.generate(() -> Math.round(Math.random() * 100)).limit(100).collect(Collectors.toList()).forEach(System.out::println);

        List<Integer> list1 = Stream.iterate(0,n -> n + 1).limit(50).collect(Collectors.toList());
        list1.stream().skip(40).limit(5).forEach(System.out::println);
        System.out.println(list1.get(0));

        System.out.println("---------------------------------------------------------------");
        list1.stream().skip(47).limit(5).forEach(System.out::println);

        System.out.println("---------------------------------------------------------------");

        List<Integer> list2 = list1.subList(10,14);

        list2.stream().forEach(System.out::println);

        System.out.println("---------------------------------------------------------------");

        List<Integer> list3 = list1.subList(0,4);
        list3.stream().forEach(System.out::println);



        System.out.println("---------------------------------------------------------------");

        String s = "sdkjfsldjslkdsjldkfjsldkfjldsfsldksjldf";
        System.out.println(s.substring(0,5));
    }
}
