package com.jiangcz.theory.thread.consumer;

/**
 * 类名称：TestProduceConsume<br>
 * 类描述：生产消费者模式 见设计模式模块中的生产消费者模式3中模式<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class TestProduceConsume {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer p1 = new Producer(clerk);
        Consumer c1 = new Consumer(clerk);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(c1);
        Thread t3 = new Thread(p1);

        t1.setName("生产者1号");
        t2.setName("消费者1号");
        t3.setName("生产者2号");

        t1.start();
        t2.start();
        t3.start();
    }
}
