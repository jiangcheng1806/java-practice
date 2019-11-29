package com.jiangcz.application.jdk.lambda;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.PrintStream;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * 类名称：LambdaTest<br>
 * 类描述：lambda测试http://www.importnew.com/16436.html<br>
 * 创建时间：2018年05月21日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class LambdaTest {
    public static void main(String[] args) {

        //new LambdaTest().testStaticMethod();

        C c = new C(){};
        c.test();
    }

    /**
     * 默认方法的重写
     */
    public interface E {

        default void test(){
            System.out.println("默认方法");
        }

    }

    interface F extends E{
        // 在子接口重写父接口的默认方法，把默认方法改为抽象方法
        @Override
        void test();
    }

    static class RemoveDefaultMethod {

        public static void main(String[] args) {
            E e = new E(){};
            e.test();
            F f = () -> System.out.println("匿名内部类重写父接口方法");
            f.test();
        }

    }

    /**
     * 默认方法多继承
     */
    interface A{
        default void test(){
            System.out.println("接口A里面的默认方法");
        }
    }

    interface B{
        default void test(){
            System.out.println("接口B里面的默认方法");
        }
    }

    public interface C extends A, B {
        // 明确指定引用父接口,使用super引用调用父接口的默认方法
        // <父接口类名>.super.<重复的方法名>([参数])
        @Override
        default void test(){
            B.super.test();
            System.out.println("接口C重写的默认test方法");
        }

    }


    /**
     * 接口中写默认方法
     */
    public interface TestDefaultMethod {

        // 使用 default 修改的方法,表示实例方法, 必须通过实例来方法
        public default void test(){
            System.out.println("这个是接口里面的默认方法" + this);
        }

        public static void main(String[] args) {
            // 使用匿名内部类初始化实例
            TestDefaultMethod tdm = new TestDefaultMethod() {};
            // 使用对象来访问默认方法
            tdm.test();
        }

    }


    /**
     * 接口中写静态方法
     */
    public void testStaticMethod(){

        // 调用接口的静态方法
        TestStaticMethod.test1();

    }

    public interface TestStaticMethod {

        // 这是一个函数式接口,因为这个接口里面只有一个抽象方法
        public void test();

        // 静态方法不是抽象方法
        static void test1(){
            System.out.println("这个是接口里面的静态方法，直接可以使用接口调用此方法");
        }

        public static void main(String[] args) {
            System.out.println("自从接口可以有静态方法，从此接口可以写main方法,可以作为程序的入口");
            TestStaticMethod.test1();
        }

    }



    /**
     * lambda实现interface
     */
    public void testInterface(){

        PrintUtil printUtil = () -> System.out.println("test implement a interface");

        printUtil.print();

        PrintUtil2 printUtil2 = i -> System.out.println("一个参数，一行代码输出参数的值 : " + i);

        printUtil2.print2(56);

        PrintUtil3 printUtil3 = i -> i * i;
        System.out.println(printUtil3.print3(56));

        MethodRef f = s -> System.out.println(s);
        f.print3("跟第2个不是一回事吗");

        // 能够根据函数式接口的方法参数，推断引用的方法的参数的数据类型
        // 不引用方法进行排序
        MethodRef1 r3 = (o) -> Arrays.sort(o);
        // 引用类方法
        MethodRef1 r2 = Arrays :: sort;
        int[] a = new int[]{4, 12, 32, 44, 5, 9};
        // 引用方法排序
        r2.sort(a);
        // 引用方法输出
        f.print3(Arrays.toString(a));


        // *** 引用类的实例方法
        MethodRef2 r4 = PrintStream :: println;
        // 第二个之后的参数,作为引用方法的参数
        r4.test(System.out, "第二个参数");



        // 引用构造器,根据函数式接口的方法名来推断引用哪个构造器
        MethodRef4 r5 = String :: new;
        String ok = r5.test(new char[]{'阿' , '器'});
        System.out.println(ok);

        MethodRef4 r6 = (c) -> {return new String(c);};
        String o1 = r6.test(new char[]{'阿' , '器'});
        System.out.println(o1);


    }

    /**
     * 无参数，无返回值
     * FunctionalInterface 这个注解是为了强制限定只能有一个方法
     */
    @FunctionalInterface
    interface PrintUtil{
        void print();

        //void print2();
    }

    /**
     * 有一个参数，无返回值
     * FunctionalInterface 这个注解是为了强制限定只能有一个方法
     */
    @FunctionalInterface
    interface PrintUtil2{
        void print2(Integer i);

        //void print2();
    }

    /**
     * 有一个参数，有一个返回值
     * FunctionalInterface 这个注解是为了强制限定只能有一个方法
     */
    @FunctionalInterface
    interface PrintUtil3{
        Integer print3(Integer i);

        //void print2();
    }

    /**
     * 方法引用
     * FunctionalInterface 这个注解是为了强制限定只能有一个方法
     */
    @FunctionalInterface
    interface MethodRef{
        void print3(String s);

        //void print2();
    }

    /**
     * 方法引用
     * FunctionalInterface 这个注解是为了强制限定只能有一个方法
     */
    @FunctionalInterface
    interface MethodRef1{
        void sort(int[] arr);

        //void print2();
    }

    /**
     * 方法引用
     * FunctionalInterface 这个注解是为了强制限定只能有一个方法
     */
    @FunctionalInterface
    interface MethodRef2{
        void test(PrintStream out,String str);

        //void print2();
    }

    // 测试构造器引用
    @FunctionalInterface
    interface MethodRef4{
        String test(char[] str);
    }


    /**
     * list 转 map
     */
    public void testList2Map(){

        List<Student> students = new ArrayList<>();
        for (int i = 1;i < 5;i++){

            students.add(new Student("jiangcheng"+i,"123456"+i));
        }

        Map<String,String> stuMap = students.stream().collect(Collectors.toMap(Student::getUsername,Student::getPassword));

        System.out.println(stuMap);

        Map stuMap1 = students.stream().collect(Collectors.toMap(Student::getUsername,Function.identity()));

        System.out.println(stuMap1);

        Map<String,Student> stuMap2 = students.stream().collect(Collectors.toMap(Student::getUsername,student -> student,(student, student2) -> student));

        System.out.println(stuMap2);

        // 将Stream转换成容器或Map
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Map<String, Integer> strMap = stream.collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(strMap);

        //将 List<String> 转换成容器
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        Map<String,Integer> feMap = features.stream().collect(Collectors.toMap(Function.identity(),String::length));
        System.out.println(feMap);


        // <- This,
        Map<String,String> absMap = Arrays.asList("a", "b", "c")
                .stream()          // <- is the same as this.
                .collect(Collectors.toMap(
                        Function.identity(), // <-- And this,
                        str -> str));        // <-- is the same as this.
        System.out.println(absMap);

    }

    @AllArgsConstructor
    @NoArgsConstructor
    class Student{

        private String username;
        private String password;



        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    /**
     * 用lambda表达式实现Runnable
     */
    public void testRunnable(){

        // Java 8之前：
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();

        //Java 8方式：
        new Thread(() -> System.out.println("In java8,Lambda expression rocks!!")).start();
    }


    /**
     * 使用lambda表达式对列表进行迭代
     *
     */
    public void testForEach(){
        // Java 8之前：
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String feature : features) {
            System.out.println("jdk7-:"+feature);
        }

        features.forEach( n -> System.out.println("jdk8+:"+n));

        features.forEach( System.out :: println);
    }

    /**
     * 使用lambda表达式和函数式接口Predicate
     */
    public void testCondition(){
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages,((str) -> str.toString().startsWith("J")));

        System.out.println("Languages which ends with a ");
        filter(languages,(str) -> str.toString().endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages,(str) -> true);

        System.out.println("Print no language : ");
        filter(languages,(str)->false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages,(str)->str.toString().length()>4);
    }

    public static void filter(List<String> names, Predicate condition){
        /*for (String name : names){
            if (condition.test(name)){
                System.out.println(name + "");
            }
        }*/
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + "--------------->it's jdk8 method result");
        });
    }

    /**
     * 如何在lambda表达式中加入Predicate
     */
    public void testPredicate(){
        List names = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        // 甚至可以用and()、or()和xor()逻辑函数来合并Predicate，
// 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
        Predicate startsWtithJ = (n)->n.toString().startsWith("J");

        Predicate fourLetterLong = (n)->n.toString().length()==4;

        names.stream().filter(startsWtithJ.and(fourLetterLong)).forEach((n)-> System.out.println("nName, which starts with 'J' and four letter long is : " + n));
    }


    /**
     * Java 8中使用lambda表达式的Map和Reduce示例
     */
    public void testMapResuce(){
        System.out.println("jdk7-:");
        // 不使用lambda表达式为每个订单加上12%的税
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            System.out.println(price);
        }
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            total = total + price;
        }
        System.out.println("Total : " + total);
        System.out.println("jdk8+:");
        costBeforeTax.stream().map((cost)->cost+.12*cost).forEach(System.out::println);

        double bill = costBeforeTax.stream().map((cost)->cost+0.12*cost).reduce((sum,cost)->sum+cost).get();
        System.out.println("Total:"+bill);
    }

    /**
     * filter
     */
    public void testFilter(){
        List<String> strList = Arrays.asList("abc","","bcd","","defg","jk");
        // 创建一个字符串列表，每个字符串长度大于2
        List<String> filtered = strList.stream().filter(x->x.length()>2).collect(toList());

        System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
    }

    /**
     * function
     */
    public void testJoining(){
        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map(x->x.toUpperCase()).collect(joining(","));
        System.out.println(G7Countries);
    }

    /**
     * dstinct
     */
    public void testDistinct(){
        // 用所有不同的数字创建一个正方形列表
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map(i->i*i).distinct().collect(toList());
        System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
    }


    /**
     * lambda 统计
     */
    public void testAnalysis(){
        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x)->x).summaryStatistics();
        System.out.println("Highest prime number in List : "+stats.getMax());
        System.out.println("Lowest prime number in List : "+stats.getMin());
        System.out.println("Sum of all prime numbers : "+stats.getSum());
        System.out.println("Average of all prime numbers : "+stats.getAverage());
    }

}
