package com.jiangc.practice.jdk.thread;

import java.util.concurrent.*;

public class ThreadPoolExceptionDemo3 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //1.创建一个自己定义的线程池
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                3,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue(10)
        ) {
            //重写afterExecute方法
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
//                1.0 简单的处理，只能处理execute方法的
//                System.out.println("afterExecute里面获取到异常信息，处理异常" + t.getMessage());

                //这个是excute提交的时候
                if (t != null) {
                    System.out.println("afterExecute里面获取到excute提交的异常信息，处理异常" + t.getMessage());
                }
                //如果r的实际类型是FutureTask 那么是submit提交的，所以可以在里面get到异常
                if (r instanceof FutureTask) {
                    try {
                        Future<?> future = (Future<?>) r;
                        //get获取异常
                        future.get();

                    } catch (Exception e) {
                        System.out.println("afterExecute里面获取到submit提交的异常信息，处理异常" + e);
                    }
                }


            }
        };

        //当线程池抛出异常后 execute
        executorService.execute(new Task3());

        //当线程池抛出异常后 submit
        executorService.submit(new Task3());
    }
}
class Task3 implements Runnable {

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