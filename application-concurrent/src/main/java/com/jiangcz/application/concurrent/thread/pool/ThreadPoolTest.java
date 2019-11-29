package com.jiangcz.theory.thread.pool;

/**
 * 类名称：ThreadPoolTest<br>
 * 类描述：<br>
 * 创建时间：2019年04月04日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class ThreadPoolTest {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {

            ThreadPool.getInstance().start(new Runnable() {
                @Override
                public void run() {
                    try {
                        //休眠100ms
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
