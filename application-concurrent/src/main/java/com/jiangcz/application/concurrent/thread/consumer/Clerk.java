package com.jiangcz.theory.thread.consumer;

/**
 * 类名称：Clerk<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Clerk {//店员
    int product;

    synchronized void addProduct(){
        if (product >= 20){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            product++;
            System.out.println(Thread.currentThread().getName()+"生产第"+product+"产品");
            notifyAll();
        }
    }

    synchronized void reduceProduct(){
        if (product <= 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Thread.currentThread().getName()+"消费了第"+product+"个产品");
        }
    }
}
