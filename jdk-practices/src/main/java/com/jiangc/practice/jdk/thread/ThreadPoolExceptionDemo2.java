package com.jiangc.practice.jdk.thread;

import java.util.concurrent.*;

public class ThreadPoolExceptionDemo2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //1.实现一个自己的线程池工厂
        ThreadFactory factory = (Runnable r) -> {
          //创建一个线程
            Thread t = new Thread(r);
            //给创建的线程设置UncaughtExceptionHandler对象 里面实现异常的默认逻辑
            t.setUncaughtExceptionHandler((Thread thread1,Throwable e)->{
                System.out.println("线程工厂设置的exceptionHandler" + e.getMessage());
            });
            return t;
        };


        //创建一个线程池
        ExecutorService executorService =
                new ThreadPoolExecutor(1,1,0,
                        TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(10),factory);


        //当线程池抛出异常后 submit无提示 其他线程继续执行
        Future submit = executorService.submit(new Task2());
//都要弄异常结果
        System.out.println(submit.get());
//线程池使用submit不抛异常一定要只有再使用future的情况下才使用submit

        //submit 无提示
        Thread.sleep(1000);
        System.out.println("==================为检验打印结果，1秒后执行execute方法");


        // execute 方法被线程工厂factory 的UncaughtExceptionHandler捕捉到异常
        executorService.execute(new Task2());
    }
}
class Task2 implements Runnable {

    @Override
    public void run() {
        System.out.println("进入task方法");
//        try {
            int i = 1/0;
//        } catch (Exception e) {
////            throw new RuntimeException(e);
//            System.out.println("使用try catch捕获异常" + e);
//        }
    }
}