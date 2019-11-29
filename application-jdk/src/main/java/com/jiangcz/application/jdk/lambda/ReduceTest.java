package com.jiangcz.application.jdk.lambda;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

/**
 * 类名称：ReduceTest<br>
 * 类描述：<br>
 * 创建时间：2019年03月06日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class ReduceTest {


    /**
     * (acc,item) -> acc+=item
     * @param args
     */
    public static void main1(String[] args) {

        Optional accResult = Stream.of(1,2,3,4).reduce((acc,item) -> {
            System.out.println("acc : " + acc);
            acc += item;
            System.out.println("item : " + item);
            System.out.println("acc : " + acc);
            System.out.println("---------------------");
            System.out.println();
            return acc;
        });


        System.out.println("accResult: " + accResult.get());
        System.out.println("-----------------------------");
    }

    /**
     * (acc,item) -> acc+=item
     * @param args
     */
    public static void main2(String[] args) {

        int accResult = Stream.of(1,2,3,4).reduce(0,(acc,item) -> {
            System.out.println("acc : " + acc);
            acc += item;
            System.out.println("item : " + item);
            System.out.println("acc : " + acc);
            System.out.println("---------------------");
            System.out.println();
            return acc;
        });


        System.out.println("accResult: " + accResult);
        System.out.println("-----------------------------");
    }

    /**
     * bifunction operator
     * @param args
     */
    public static void main3(String[] args) {

        ArrayList<Integer> accResult = Stream.of(1,2,3,4).reduce(new ArrayList<Integer>(),
                (acc, item) -> {

                    acc.add(item);
                    System.out.println("item: " + item);
                    System.out.println("acc: " + acc);
                    System.out.println("BiFunction");

                    return acc;
                }, (acc, item) -> {

                    System.out.println("BinaryOperator");

                    acc.addAll(item);
                    System.out.println("item: " + item);
                    System.out.println("acc: " + acc);
                    System.out.println("--------------------");

                    return acc;
                });

        System.out.println("accResult: " + accResult);
        System.out.println("-----------------------------");
    }

    /**
     * Integer::sum
     * @param args
     */
    public static void main4(String[] args) {

        int accResult = Stream.of(1,2,3,4).reduce(0,Integer::sum);

        System.out.println("accResult: " + accResult);
        System.out.println("-----------------------------");

    }

    /**
     * BigDecimal::add
     * @param args
     */
    public static void main5(String[] args) {

        BigDecimal accResult = Stream.of(new BigDecimal(0),new BigDecimal(2),new BigDecimal(3),new BigDecimal(4)).reduce(new BigDecimal(0),BigDecimal::add);

        System.out.println("accResult: " + accResult);
        System.out.println("-----------------------------");
    }


}
