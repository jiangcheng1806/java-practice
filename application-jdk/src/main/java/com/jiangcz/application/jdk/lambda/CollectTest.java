package com.jiangcz.application.jdk.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类名称：CollectTest<br>
 * 类描述：<br>
 * 创建时间：2019年03月14日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class CollectTest {


    public static void main(String[] args) {

        System.out.println("hello world");

        CollectTest collectTest = new CollectTest();
        //collectTest.testGroupBy();
        //collectTest.testSum();
        collectTest.testFilter();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class A {
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 过滤
     */
    public void testFilter(){
        List<A> aList = new ArrayList<>();
        aList.add(new A(0,"jiangcheng"));
        aList.add(new A(1,"chenmei"));
        aList.add(new A(2,"liqian"));

        /// 测试 提交
        aList.clear();
        System.out.println(aList);
        aList.stream().filter(a -> a.id > 1).collect(Collectors.toList());
        System.out.println(aList);

    }

    /**
     * lambda toMap
     */
    public void testToMap(){
        List<A> aList = new ArrayList<>();
        aList.add(new A(0,"jiangcheng"));
        aList.add(new A(1,"chenmei"));
        aList.add(new A(2,"liqian"));


        System.out.println(aList);

        Map<Integer,A> aMap = new HashMap<>();
        aMap.put(4,new A(4,"pikaqiu"));

        aMap = aList.stream().filter(a -> a.id > 1).collect(Collectors.toMap(A::getId, a -> a,(a, a2) -> a));

        System.out.println(aMap);

        Map<Integer,A> bMap = new HashMap<>();
        aMap.put(4,new A(4,"pikaqiu"));

        bMap = aList.stream().filter(a -> a.id > 1).collect(Collectors.toMap(A::getId, a -> a,(a, a2) -> a,HashMap::new));

        System.out.println(bMap);
    }

    /**
     * 去重
     */
    public void testIsolate(){
        List<A> aList = new ArrayList<>();
        aList.add(new A(0,"jiangcheng"));
        aList.add(new A(1,"chenmei"));
        aList.add(new A(2,"liqian"));
        aList.add(new A(2,"liqian2"));


        System.out.println(aList);

        aList = aList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingInt(A::getId))),ArrayList::new));

        System.out.println(aList);
    }

    /**
     * 最小
     */
    public void testMaxAndMin(){
        List<A> aList = new ArrayList<>();
        aList.add(new A(0,"jiangcheng"));
        aList.add(new A(1,"chenmei"));
        aList.add(new A(2,"liqian"));
        aList.add(new A(2,"liqian2"));


        System.out.println(aList);

        Optional<A> maxA = aList.stream().collect(Collectors.maxBy(Comparator.comparingInt(A::getId)));

        maxA.ifPresent(System.out::println);

        Optional<A> minA = aList.stream().collect(Collectors.minBy(Comparator.comparingInt(A::getId)));

        minA.ifPresent(System.out::println);
    }

    /**
     * 求和
     */
    public void testSum(){

        List<A> aList = new ArrayList<>();
        aList.add(new A(0,"jiangcheng"));
        aList.add(new A(1,"chenmei"));
        aList.add(new A(2,"liqian"));
        aList.add(new A(2,"liqian2"));
        aList.add(new A(null,"liqian3"));


        System.out.println(aList);

        //Integer sumA = aList.stream().map(A::getId).reduce(0,Integer::sum);

        Integer sumA = aList.stream().map(A::getId).reduce(0,(a,b) -> {if (b != null){
            a += b;
            }
            return a;
        });

        System.out.println(sumA);

        //Optional<Integer> opSumA = aList.stream().map(A::getId).reduce(Integer::sum);//仍然出现空指针

        //System.out.println(opSumA.orElse(0));

        //Optional<Integer> opSumA = aList.stream().map(A::getId).map(Optional::of).map(v -> v.orElse(0)).reduce(Integer::sum);//仍然出现空指针

        //System.out.println(opSumA.orElse(0));

        //List<Optional<Integer>> list =  aList.stream().map(A::getId).map(Optional::of).collect(Collectors.toList()); // 仍然出现空指针

        //System.out.println(list);


        BigDecimal sumB = Stream.of(new BigDecimal(1),new BigDecimal(2),new BigDecimal(4)).reduce(BigDecimal.ZERO,BigDecimal::add);

        System.out.println(sumB);



    }

    /**
     * 分组
     */
    public void testGroupBy(){

        List<A> aList = new ArrayList<>();
        aList.add(new A(0,"jiangcheng"));
        aList.add(new A(1,"chenmei"));
        aList.add(new A(2,"liqian"));
        aList.add(new A(2,"liqian2"));


        System.out.println(aList);//[CollectTest.A(id=0, name=jiangcheng), CollectTest.A(id=1, name=chenmei), CollectTest.A(id=2, name=liqian), CollectTest.A(id=2, name=liqian2)]

        //参数 是 Funtion key是泛型
        Map<Integer, List<A>> groupByA = aList.stream().collect(Collectors.groupingBy(A::getId));

        System.out.println(groupByA);//{0=[CollectTest.A(id=0, name=jiangcheng)], 1=[CollectTest.A(id=1, name=chenmei)], 2=[CollectTest.A(id=2, name=liqian), CollectTest.A(id=2, name=liqian2)]}

        //参数是Predicate 接口 所以返回key一定是boolean类型
        Map<Boolean,List<A>> partitionA = aList.stream().collect(Collectors.partitioningBy(o -> o.getId() % 2 == 1));

        System.out.println(partitionA);//{false=[CollectTest.A(id=0, name=jiangcheng), CollectTest.A(id=2, name=liqian), CollectTest.A(id=2, name=liqian2)], true=[CollectTest.A(id=1, name=chenmei)]}



    }
}
