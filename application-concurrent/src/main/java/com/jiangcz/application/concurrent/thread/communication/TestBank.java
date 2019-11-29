package com.jiangcz.theory.thread.communication;

/**
 * 类名称：TestBank<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class TestBank {
    public static void main(String[] args) {
        Account acc = new Account();
        Customer c1 = new Customer(acc);
        Customer c2 = new Customer(acc);

        c1.setName("用户1");
        c2.setName("用户2");

        c1.start();
        c2.start();
    }
}
