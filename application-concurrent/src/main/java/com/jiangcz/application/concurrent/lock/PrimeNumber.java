package com.jiangcz.application.concurrent.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 类名称：PrimeNumber<br>
 * 类描述：循环屏障   主要是CyclicBarrier 类<br>
 * 创建时间：2018年08月01日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class PrimeNumber {
    private static final int TOTAL_COUNT = 5000;
    private static final int RANGE_LENGTH = 200;
    private static final int WORKER_NUMBER = 5;
    private static volatile boolean done = false;
    private static int rangeCount = 0;
    private static final List<Long> results = new ArrayList<>();

    private static final CyclicBarrier barrier = new CyclicBarrier(WORKER_NUMBER, new Runnable() {
        @Override
        public void run() {
            if (results.size() > TOTAL_COUNT){
                done = true;
            }
        }
    });

    private static class PrimeFinder implements Runnable {
        @Override
        public void run() {// 整个过程在一个 while循环下，await()等待，下次循环开始，会再次判断 执行条件
            while (!done){
                int range = getNextRange();
                long start = range * RANGE_LENGTH;
                long end = (range + 1) * RANGE_LENGTH;
                for (long i = start; i < end; i++) {
                    if (isPrime(i)){
                        updateResult(i);
                    }
                }
                try{
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    done = true;
                }
            }
        }
    }

    private synchronized static void updateResult(long value){
        results.add(value);
    }

    private synchronized static int getNextRange() {
        return rangeCount++;
    }

    private static boolean isPrime(long number){
        return true;//判断指数
    }

    public void calculate(){
        for (int i = 0; i < WORKER_NUMBER; i++) {
            new Thread(new PrimeFinder()).start();
        }
        while (!done){

        }
        //计算完成
    }
}
