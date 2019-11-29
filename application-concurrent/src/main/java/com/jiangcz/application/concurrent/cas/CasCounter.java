package com.jiangcz.application.concurrent.cas;

/**
 * 类名称：CasCounter<br>
 * 类描述：非阻塞的计数器<br>
 * 创建时间：2018年07月17日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class CasCounter {

    private SimulatedCAS value;

    public int getValue(){
        return value.get();
    }

    public int increment(){
        int v;
        do {
            v = value.get();
        } while (v != value.compareAndSwap(v,v+1));
        return v+1;
    }
}
