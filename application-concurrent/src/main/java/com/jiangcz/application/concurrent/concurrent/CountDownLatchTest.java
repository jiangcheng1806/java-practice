package com.jiangcz.application.concurrent.concurrent;

import java.text.MessageFormat;
import java.util.concurrent.CountDownLatch;

/**
 * 类名称：CountDownLatchTest<br>
 * 类描述：类似计数器的功能 使主线程等待其他线程执行完毕之后再进行<br>
 * 创建时间：2019年04月19日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class CountDownLatchTest {


    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(2);

        aaa(latch);


        aaa(latch);


        try {
            System.out.println("等待2个子线程执行完毕。。。");

            latch.await();
            System.out.println("2 个子线程已执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void aaa(CountDownLatch latch) {
        new Thread(() -> {

            try {
                System.out.println(MessageFormat.format("子线程{0}正在执行", Thread.currentThread().getName()));
                Thread.sleep(3000);
                System.out.printf("子线程%s执行完毕%n", Thread.currentThread().getName());
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
