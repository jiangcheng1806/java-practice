package com.jiangcz.application.jdk.structure.outinner;

/**
 * 类名称：Outer<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Outer {
    class Inner {
        public Inner(String s){
            System.out.println(s);
        }
    }
}
