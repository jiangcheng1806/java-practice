package com.jiangcz.application.jdk.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁demo lock 必须跟 unlock 次数一致
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        //ReentrantLock和synchronized都是可重入的。synchronized因为可重入因此可以放在被递归执行的方法上,且不用担心线程最后能否正确释放锁；而ReentrantLock在重入时要却确保重复获取锁的次数必须和重复释放锁的次数一样，否则可能导致其他线程无法获得该锁。
        for (int i = 0; i < 3; i++) {
            lock.lock();
        }
        for (int i = 0; i < 3; i++) {
            try {

            } finally {
                lock.unlock();
            }
        }

    }
}
