package com.jiangc.practice.algorithm.coherence;

import java.util.Scanner;

/**
 * 类名称：Coherence<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Coherence {


    /**
     *
     * 求余数（同余定理）~
     *
     * 现在给你一个自然数n，它的位数小于等于一百万，现在你要做的就是求出这个数除10003之后的余数
     *
     * 输入描述：
     * 第一行有一个整数m(1<=m<=8),表示有m组测试数据；
     * 随后m行每行有一个自然数n。
     *
     * 输出描述：
     * 输出n整除10003之后的余数，每次输出占一行。
     *
     * 样例输入
     * 3
     * 4
     * 5
     * 465456541
     *
     * 样例输出
     * 4
     * 5
     * 6948
     *
     * 同余定理:
     * (a+b)%c=((a%c)+(b%c))%c
     * 例如：
     * 123%n = (((1%n * 10%n+2%n)%n * 10%n)%n+3%n)%n
     *
     * 此题求余数有两种做法，一种是用模拟的方法模拟出大数除法从而求得余数，本代码使用的是同余定理 解题，更加简单。
     * #include<iostream>
     * #include<string>
     * #include<string.h>
     * using namespace std;
     *  int main()
     * {
     *     int num,t,i,len;
     *     char n[1000000];
     *     cin>>t;
     *     while(t--)
     *     {
     *         num=0;
     *         cin>>n;
     *         len=strlen(n);
     *         for(i=0;i<len;++i)
     *         {
     *         num=(num*10+(int)n[i]-'0')%10003;
     *         }
     *         cout<<num<<endl;
     *     }
     *     return 0;
     *  }
     */
    public static void main(String[] args) {
        int num,t,i,len;
        char[] n = new char[1000000];
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        while (t-- > 0){
            num = 0;
            n = sc.nextLine().toCharArray();
            len = n.length;
            for ( i = 0; i < len; i++) {
                num = (num*10 + (int)n[i]-'0') % 10003;
            }
            System.out.println(num);
        }
        return;
    }
}
