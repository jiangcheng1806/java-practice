package com.jiangcz.application.concurrent.concurrent;

import java.text.MessageFormat;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 类名称：CyclicBarrierTest<br>
 * 类描述：第四个线程启动延迟 前面三个到达barrier 之后 等待了指定的时间之后 发现第四个线程还没有到达 barrier 就抛出异常继续执行后面的任务<br>
 * 创建时间：2019年04月19日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class CyclicBarrierTest2 {


    public static void main(String[] args) {
        int N  = 4;
        CyclicBarrier barrier = new CyclicBarrier(N, () -> System.out.println(MessageFormat.format("当前线程 {0}",Thread.currentThread().getName())));
        for (int i = 0; i < N; i++) {
            if (i < N-1)
                new Writer(barrier).start();
            else {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Writer(barrier).start();
            }
        }
    }

    static class Writer extends Thread{

        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(MessageFormat.format("线程 {0} 正在写入数据。。。",Thread.currentThread().getName()));
            try {
                Thread.sleep(5000);
                System.out.println(MessageFormat.format("线程 {0} 写入数据完毕,等待其他线程写入完毕",Thread.currentThread().getName()));
                cyclicBarrier.await(2000,TimeUnit.MILLISECONDS);
            } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                e.printStackTrace();
            }

            System.out.println("所有线程写入完毕，继续处理其他任务。。。");
        }
    }
}
