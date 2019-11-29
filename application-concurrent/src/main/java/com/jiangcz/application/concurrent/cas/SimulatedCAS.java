package com.jiangcz.application.concurrent.cas;


/**
 * 类名称：SimulatedCAS<br>
 * 类描述：模拟cas操作<br>
 * 创建时间：2018年07月17日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class SimulatedCAS {

    @GuardedBy("this") private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue,int newValue){ //比较并交换返回设置之前的值

        int oldValue = value;
        if (oldValue==expectedValue){
            value = newValue;
        }

        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue,int newValue){ //比较并设置,返回true或false

        return (expectedValue==compareAndSwap(expectedValue,newValue));

    }
}
