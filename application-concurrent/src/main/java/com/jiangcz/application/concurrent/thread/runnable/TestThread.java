package com.jiangcz.theory.thread.runnable;

/**
 * 类名称：TestThread<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class TestThread {
    public static void main(String[] args) {
        PrintNum p = new PrintNum();
        Thread t1 = new Thread(p);
        t1.start();
    }
}
