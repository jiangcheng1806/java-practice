package com.jiangc.practice.designpattern.consumer;

/**
 * 类名称：WarehouseTest<br>
 * 类描述：<br>
 * 创建时间：2018年08月28日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class WarehouseTest {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse(30);
        Consumer c1 = new Consumer(50,warehouse);
        Consumer c2 = new Consumer(20,warehouse);
        Consumer c3 = new Consumer(30,warehouse);
        Producer p1 = new Producer(10,warehouse);
        Producer p2 = new Producer(10,warehouse);
        Producer p3 = new Producer(10,warehouse);
        Producer p4 = new Producer(10,warehouse);
        Producer p5 = new Producer(10,warehouse);
        Producer p6 = new Producer(10,warehouse);
        Producer p7 = new Producer(10,warehouse);

        c1.start();
        c2.start();
        c3.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();

    }
}
