package com.jiangc.practice.algorithm.lineup;

/**
 * 类名称：LineUp<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class LineUp {
    /**
     *
     * 5个人排队,解散后再排,每个人都不排回原来的位置,有多少种排法?
     *
     * 问题可用公式解决： n个人每个人都不站在原来的位置的方法数有: f(n)=n!(1/2!-1/3!+1/4!+..+(-1)^n/n!)
     *
     * 但是一般都是问的前五个数的值，记住就可以了
     * f(2)=1,f(3)=2,f(4)=9,f(5)=44
     *
     *
     */
    public static void main(String[] args) {

    }
}
