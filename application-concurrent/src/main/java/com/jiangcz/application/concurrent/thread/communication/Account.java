package com.jiangcz.theory.thread.communication;

import static java.lang.Thread.sleep;

/**
 * 类名称：Account<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Account {
    double balance;
    public Account(){

    }

    synchronized void deposit(double amt){
        notify();
        balance += amt;
        try {
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":"+balance);
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
