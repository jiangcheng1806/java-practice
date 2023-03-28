package com.jiangc.practice.designpattern.consumer;

/**
 * 类名称：Producer<br>
 * 类描述：<br>
 * 创建时间：2018年08月28日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Producer extends Thread {
    private int need; //生产产品的数量
    private Warehouse warehouse; // 仓库

    Producer(int need,Warehouse warehouse){
        this.need = need;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        //生产指定数量的产品
        warehouse.produce(need);
    }
}
