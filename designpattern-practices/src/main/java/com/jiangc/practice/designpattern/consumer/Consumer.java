package com.jiangc.practice.designpattern.consumer;

/**
 * 类名称：Consumer<br>
 * 类描述：<br>
 * 创建时间：2018年08月28日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Consumer extends Thread {
    private int need; //生产产品的数量
    private Warehouse warehouse; //仓库

    Consumer(int need,Warehouse warehouse){
        this.need = need;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        //消费指定数量的产品
        warehouse.consume(need);
    }
}
