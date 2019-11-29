package com.jiangcz.theory.thread.synchronize;

/**
 * 类名称：Window<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Window extends Thread {
    static int ticket = 100;
    static Object obj = new Object();

    @Override
    public void run() {
        while (true){
            synchronized (obj){
                if (ticket > 0){
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" "+ticket--);
                }
            }
        }
    }
}
