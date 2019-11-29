package com.jiangcz.application.jdk.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 类名称：CollectTest2<br>
 * 类描述：<br>
 * 创建时间：2019年07月22日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class CollectTest2 {

    public static void main(String[] args) {

        List<De> des = new ArrayList<>();
        List<List<String>> m2dList = IntStream.range(0,9).mapToObj(i -> IntStream.range(0,10).mapToObj(j -> Integer.toString(i * 10 + j)).collect(Collectors.toList())).collect(Collectors.toList());

        des = IntStream.range(0,9).mapToObj(i -> {
            De a = new De();
            a.setS1("aa"+i);
            a.setS2("bb"+i);
            return a;
        }).collect(Collectors.toList());

        //System.out.println(ToStringBuilder.reflectionToString(des));
        des.stream().forEach(System.out::println);


        des.stream().forEach(a -> a.setS2("test"));

        des.stream().forEach(System.out::println);
    }

    static class De{

        private String s1;
        private String s2;

        public String getS1() {
            return s1;
        }

        public void setS1(String s1) {
            this.s1 = s1;
        }

        public String getS2() {
            return s2;
        }

        public void setS2(String s2) {
            this.s2 = s2;
        }

        @Override
        public String toString() {
            return "De{" +
                    "s1='" + s1 + '\'' +
                    ", s2='" + s2 + '\'' +
                    '}';
        }
    }


}
