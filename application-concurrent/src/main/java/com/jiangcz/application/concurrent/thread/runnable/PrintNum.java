package com.jiangcz.theory.thread.runnable;

/**
 * 类名称：PrintNum<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class PrintNum implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <100; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}
