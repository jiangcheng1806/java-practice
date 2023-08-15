package com.jiangc.practice.jdk.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExceptionDemo1 {
    public static void main(String[] args) {
        //创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        //当线程池抛出异常后 submit无提示 其他线程继续执行
        executorService.submit(new Task1());

        //当线程池抛出异常后 execute 抛出异常，其他线程继续执行任务
        executorService.execute(new Task1());
    }
}
class Task1 implements Runnable {

    @Override
    public void run() {
        System.out.println("进入task方法");
        try {
            int i = 1/0;
        } catch (Exception e) {
//            throw new RuntimeException(e);
            System.out.println("使用try catch捕获异常" + e);
        }
    }
}