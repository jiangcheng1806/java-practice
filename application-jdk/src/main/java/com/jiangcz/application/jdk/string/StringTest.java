package com.jiangcz.application.jdk.string;

/**
 * 类名称：StringTest<br>
 * 类描述：<br>
 * 创建时间：2018年08月31日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class StringTest {
    public static void main(String[] args) {
        /*
         * 查找子串
         */
        String str1="dwqae12232aebdalf";
        //查找指定字符第一次出现的位置
        int first1=str1.indexOf(97);//参数为字符的ascii码
        //查找指定字符串第一次出现的位置
        int first2=str1.indexOf("12");
        //查找指定字符第一次出现的位置，从索引处开始（包括索引处）
        int first3=str1.indexOf(97, 0);
        //查找指定字符串第一次出现的位置，从索引处开始（包括索引处
        int first4=str1.indexOf("12232",0);
        System.out.println("first1="+first1);
        System.out.println("first1="+first2);
        System.out.println("first1="+first3);
        System.out.println("first1="+first4);
        System.out.println("-------------");
        /*
         * 截取字符串
         */
        //从索引处到末尾（不包括索引处）
        String substr1=str1.substring(5);
        //指定区间（包括结束索引处）
        String substr2=str1.substring(5, 10);
        System.out.println("substr1="+substr1);
        System.out.println("substr2="+substr2);
        System.out.println("-------------");

        int last = str1.length();
        System.out.println(str1.substring(0,5)+"...."+str1.substring(last-5,last));


        /*
         * 分割字符串
         */
        //以a为分割字符
        String[] splitstr=str1.split("a");
        for(String res : splitstr){
            System.out.println(res);
        }
        //注：如果分割字符为正则表达式里的字符，则需要"\"做转义
    }
}
