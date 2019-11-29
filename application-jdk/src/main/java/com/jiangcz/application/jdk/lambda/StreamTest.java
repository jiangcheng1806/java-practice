package com.jiangcz.application.jdk.lambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 类名称：StreamTest<br>
 * 类描述：<br>
 * 创建时间：2019年03月07日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class StreamTest {

    /**
     * stream 转 list
     */
    public static void fun1(String[] args) {

        List<Integer> collectList = Stream.of(1,2,3,4).collect(Collectors.toList());

        System.out.println("collectList: "+collectList);
    }

    /**
     * 求最大值
     */
    public static void fun2(String[] args) {

        Optional<Integer> max = Stream.of(1,2,3,4).collect(Collectors.maxBy(Comparator.comparingInt(o -> o)));

        System.out.println("max: "+max.get());
    }

    /**
     * 分割数据块
     */
    public static void fun3(String[] args) {

        Map<Boolean,List<Integer>> partions = Stream.of(1,2,3,4).collect(Collectors.partitioningBy(it -> it % 2 == 0));

        System.out.println("partions: "+partions);
    }

    /**
     * 数据分组
     */
    public static void fun4(String[] args) {

        //Map<Boolean,List<Integer>> groups = Stream.of(1,2,3,4).collect(Collectors.groupingBy(it -> it % 2 == 0));
        Map<Boolean,List<Integer>> groups = Stream.of(1,2,3,4).collect(Collectors.groupingBy(it -> it > 3));
        System.out.println("groups: "+groups);
    }

    /**
     * 字符串拼接
     */
    public static void fun5(String[] args) {

        String joing = Stream.of("1","2","3","4").collect(Collectors.joining(",","[","]"));
        System.out.println("joing: \""+joing+"\"");
    }

    /**
     * reduce
     */
    public static void fun6(String[] args) {

        //Integer reduce1 = Stream.of(1,2,3,4).reduce(100,(sum, item) -> sum + item);
        Integer reduce2 = Stream.of(1,2,3,4).reduce(100,Integer::sum);

        System.out.println("reduce1: "+reduce2);
    }

    /**
     * orElse + findFirst
     */
    public static void fun7(String[] args) {

        //Integer reduce1 = Stream.of(1,2,3,4).reduce(100,(sum, item) -> sum + item);
        Integer first = Stream.of(1,2,3,4).findFirst().orElse(null);

        System.out.println("orElse + findFirst: "+first);
    }


    /**
     * range
     * @param args
     */
    public static void main6(String[] args) {

        double average = IntStream.range(1,10).summaryStatistics().getAverage();
        System.out.println(average);

        long sum = IntStream.range(1,10).summaryStatistics().getSum();
        System.out.println(sum);

        long count = IntStream.range(1,10).summaryStatistics().getCount();
        System.out.println(count);

    }


    /**
     * statistics
     * @param args
     */
    public static void main7(String[] args) {
        Stream<Integer> trans = Stream.of(11, 9, 2, 13, 1, 2, 99, 54, 23, 66, 70, 23, 46, 50, 100, 10, 24, 18, 19, 2);

        IntSummaryStatistics all = trans
// 前5条跳过，2, 99, 54, 23, 66, 70, 23, 46, 50, 100, 10, 24, 18, 19, 2
                .skip(5)
// 取10条考核交易 2, 99, 54, 23, 66, 70, 23, 46, 50, 100
                .limit(10)
// 将50以下的交易剔除 99, 54, 66, 70, 50, 100
                .filter(i -> i >= 50)
// 转换成数字。如果是IntStream 则不需要转换
                .mapToInt(i->i)
// 将流的统计结果放入包装对象中
                .summaryStatistics();
// 交易总量 439w，平均值为439/6
        System.out.println(all.getAverage());
    }


    /**
     *  常用流式操作
     * @param args
     */
    public static void main8(String[] args) {

        int[] nums = {2,3,4,5,6};

        // 将元素的平方打印出来
        Arrays.stream(nums)
                .map(i->i*i)
                .forEach(System.out::println);

        // 将元素中的所有偶数累加求和
        System.out.println(
                Arrays.stream(nums)
                        .map(i -> i % 2 == 0 ? i : 0)
                        .reduce(0, Integer::sum)
        );


        // flatMap处理嵌套的list
        List<List<Integer>> ll = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(11,22,33),
                Arrays.asList(0xF1,0xF2,0xF3)
        );
        ll.stream()
                .flatMap(list -> list.stream())
                .map(i -> 2 * i)
                .forEach(i -> System.out.println(i));

        //流的几种创建方式
        // 从数组创建
        int [] source = {1,2,3,4,5,6};
        IntStream s = Arrays.stream(source);
        s.forEach(System.out::println);

// 从集合创建
        List list = Arrays.asList(1,2,3,4,5);
        Stream s2 = list.stream();
        s2.forEach(System.out::println);
// 创建1到10的流
        IntStream s3 = IntStream.range(1,10);
//  直接创建
        Stream s4 = Stream.of("wo", "ai", "?");
        s4.forEach(System.out::println);
    }


    //=========================================================================================流式操作的探索====================================================================================================//
    /**
     * 流不直接修改数据
     */
    public static void main(String[] args) {

        List<String> names = Arrays.asList("zing","wang","zhen","max");
        System.out.println("\nthe source data-------------------");

        System.out.println(names);
        System.out.println("\nthe stream data-------------------");

        //map 的操作
        names.stream().map(i->{i = i+"_2 "; return i;}).forEach(System.out::print);
        System.out.println("\n");
        System.out.println(names);

        //流不直接修改数据 想将修改之后的数据返回 需要重新创建对象
        List<String> newNames = names.stream().map(i->{i = i+"_2 "; return i;}).collect(Collectors.toList());
        System.out.println("\n");
        System.out.println(newNames);

        Stream<Integer> trans = Stream.of(11, 9, 2);
        trans.forEach(i -> System.out.println(i));
        //Stream 对象只能执行一次
        ///trans.reduce(0, Integer::sum);


        //流是惰性的 这里 打印操作不会执行
        IntStream.range(1, 10)
                .limit(5)
                .filter(i -> i > 0)
                .sorted()
                .skip(1)
                .map(i -> {
                    System.out.println("正在处理" + i);
                    return i;
                });

        //除了初始创建流的，返回流对象的大部分是业务中间操作，业务中间操作（intermediate operations）只会暂是保留，不会执行，只有遇到求值终止操作（terminal operation） 时，才会一起执行。
        IntStream.range(1, 10)
                .limit(5)
                .filter(i -> i > 0)
                .sorted()
                .skip(1)
                .map(i -> {
                    System.out.println("正在处理" + i);
                    return i;
                })
                .forEach(i->{});


        //无限流
        //Stream.iterate(1,i -> i++)
        //        .forEach(num -> System.out.println(String.valueOf(num)));


        //无限流进行限制
        Stream.iterate(1,i -> i++)
                .limit(18)
                .forEach(num -> System.out.println(String.valueOf(num)));

        System.out.println("=====================");

        //无限流终止失败
        Stream.iterate(1,i -> (i+1) % 2)
                .limit(18)
                .forEach(num -> System.out.println(String.valueOf(num)));


        //短路操作
        List<String> datas = Arrays.asList("aaa","bb","CCCC","aaa bb CCCC","bb CCCC","bbb");
        datas.stream()
                .mapToInt(String::length)
                .filter(i->i==3)
                .findFirst()
                .ifPresent(System.out::println);

        datas.stream()
                .mapToInt(i -> {
                    System.out.println(i);
                    return i.length();
                })
                .filter(i->i==3)
                .findFirst()
                .ifPresent(System.out::println);

    }

}
