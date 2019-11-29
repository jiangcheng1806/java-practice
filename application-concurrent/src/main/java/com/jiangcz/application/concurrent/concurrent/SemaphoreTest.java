package com.jiangcz.application.concurrent.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 类名称：SemaphoreTest<br>
 * 类描述：<br>
 * 创建时间：2019年04月19日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class SemaphoreTest {

    public static void main(String[] args) {
        int N = 8; //工人数
        Semaphore semaphore = new Semaphore(5); // 机器数目

        for (int i = 0; i < N; i++) {
            new Worker(i,semaphore).start();
        }
    }

    static class Worker extends Thread{
        private int num;
        private Semaphore semaphore;
        public Worker(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.printf("工人 %s 占用一个机器在生产。。。%n",this.num);

                Thread.sleep(2000);
                System.out.printf("工人 %s 释放出机器 %n",this.num);

                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
