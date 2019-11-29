package com.jiangcz.theory.thread.consumer;

import static java.lang.Thread.sleep;

/**
 * 类名称：Producer<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Producer implements Runnable {
    Clerk clerk;

    Producer(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("生产者开始生产");
        while (true){
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.addProduct();
        }
    }
}
