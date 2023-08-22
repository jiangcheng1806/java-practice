package com.jiangcz.application.designpattern.callback.callback1;

/**
 * 类名称：CallBackTest<br>
 * 类描述：<br>
 * 创建时间：2019年12月13日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class CallBackTest {
    public static void main(String[] args) {
        FooBar foo = new FooBar();
        foo.setCallBack(new CallBack() {
            @Override
            public void execute() {
                System.out.println("在Test类中实现但不能被Test的对象引用,而由FooBar对象调用");
            }
        });
    }
}
