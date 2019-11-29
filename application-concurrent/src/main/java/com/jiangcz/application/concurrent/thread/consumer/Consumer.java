package com.jiangcz.theory.thread.consumer;

import static java.lang.Thread.sleep;

/**
 * 类名称：Consumer<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Consumer implements Runnable {
    Clerk clerk;

    Consumer(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("消费者开始消费");
        while (true){
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.reduceProduct();
        }
    }
}
