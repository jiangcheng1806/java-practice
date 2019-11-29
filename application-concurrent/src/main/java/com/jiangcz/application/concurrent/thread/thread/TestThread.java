package com.jiangcz.theory.thread.thread;

/**
 * 类名称：TestThread<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class TestThread {
    public static void main(String[] args) {
        SubThread s = new SubThread();
        s.setPriority(Thread.MAX_PRIORITY);
        s.start();
        s.setName("线程1");

        Thread.currentThread().setName("主线程");
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+":"+Thread.currentThread().getPriority()+"-------"+i);
        }
    }
}

class SubThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+":"+Thread.currentThread().getPriority()+"-------"+i);
        }
    }
}