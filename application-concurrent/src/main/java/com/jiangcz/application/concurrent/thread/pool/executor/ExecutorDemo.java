package com.jiangcz.theory.thread.pool.executor;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * 类名称：ExecutorDemo<br>
 * 类描述：<br>
 * 创建时间：2019年04月04日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class ExecutorDemo {

    //execute方式提交任务 不需要返回值
    public static void main_0(String[] args) {

        //使用示例1
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,5,0,TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
        executor.shutdown();
    }

    //submit方式提交任务 需要返回值
    public static void main_1(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,5,0,TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        List<Future> futureList = new Vector<>();
        //在其他线程中执行100次下列方法
        for (int i = 0; i < 100; i++) {
            futureList.add(executor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {

                    return Thread.currentThread().getName();
                }
            }));
        }

        for (int i = 0; i < futureList.size(); i++) {
            Object o = futureList.get(i).get();
            System.out.println(o.toString());
        }

        executor.shutdown();
    }

    //execute 方式提交任务 需要返回值
    public static void main_2(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        //在其他线程中执行100次下列方法
        for (int i = 0; i < 100; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }

        //执行完关闭
        executor.shutdown();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //新建一个线程池
        ExecutorService executor = Executors.newCachedThreadPool();

        List<Future> futureList = new Vector<>();
        //在其他线程中执行100次下列方法
        for (int i = 0; i < 100; i++) {

            futureList.add(executor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {

                    return Thread.currentThread().getName() + " " +System.currentTimeMillis() + " ";
                }
            }));
        }

        for (int i = 0; i < futureList.size(); i++) {
            Object o = futureList.get(i).get();
            System.out.println(o.toString() + i);
        }

        executor.shutdown();
    }
}
