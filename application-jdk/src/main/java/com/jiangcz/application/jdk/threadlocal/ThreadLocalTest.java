package com.jiangcz.application.jdk.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 避免内存泄漏的方法
 * 及时调用ThreadLocal的remove方法
 * 及时销毁线程实例
 *
 * ThreadLocalMap的key不是ThreadLocal 而是 WeakRefrence<ThreadLocal></>
 * 下次垃圾收集时就会被回收掉。如果引用ThreadLocal对象的只有ThreadLocalMap的key，那么下次垃圾收集过后该key就会变为null。
 *
 *
 * 如果这是个强引用的话，该对象将一直无法回收。因为我已经失去了其他所有该对象的外部引用，这个ThreadLocal对象将一直存在，而我却无法访问也无法回收它，导致内存泄漏。
 * 又因为ThreadLocalMap的生命周期和线程实例的生命周期一致，只要该线程一直不退出，比如线程池中的线程，那么这种内存泄漏问题将会不断积累，直到导致系统奔溃。
 *
 *
 */
public class ThreadLocalTest {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=0;i<10;i++){
            executorService.execute(new TaskThread(i));
        }

    }

    static class TaskThread implements Runnable{

        Integer taskId;

        public TaskThread(Integer taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            InheritableThreadLocalUtils.set(taskId);
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(InheritableThreadLocalUtils.get());
                }
            });
        }
    }
}
