package com.jiangcz.application.jdk.structure.outinner;

/**
 * 类名称：Test<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Test {
    class TestInner extends Outer.Inner {

        public TestInner(Outer o) {
            o.super("hello");
        }
    }

    public static void main(String[] args) {
        Outer o = new Outer();
        Test t = new Test();
        TestInner ti = t.new TestInner(o);//创建内部类时需要外部类对象引用，才能成功创建
    }
}
