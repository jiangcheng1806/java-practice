package com.jiangc.practice.designpattern.consumer;

/**
 * 类名称：Warehouse<br>
 * 类描述：仓库<br>
 * 创建时间：2018年08月28日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Warehouse {
    public static final int max_size = 100;//最大库存数量
    public int curr_size; //当前库存量

    Warehouse(){}

    Warehouse(int curr_size){
        this.curr_size = curr_size;
    }

    /**
     * 生产指定数量的产品
     */
    public synchronized void produce(int need){
        //测试是否需要生产
        while (need + curr_size > max_size){
            System.out.println("要生产的产品数量 " + need + " 超过剩余最大库存数量 " + (max_size - curr_size) + ",暂时不能执行生产任务!");
            try {
                //当前的生产线程等待
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //满足生产条件，则进行生产，这里简单的更改当前库存量
        curr_size += need;
        System.out.println("已经生产了 " + need + " 个产品，现仓库量为 " + curr_size);
        //唤醒在此对象监视器上等待的所有线程
        notifyAll();
    }

    /**
     * 消费指定数量的产品
     */
    public synchronized void consume(int need){
        //测试是否可消费
        while (curr_size < need){
            try {
                //当前线程等待
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //满足消费条件，则进行消费，这里简单的更改当前库存量
        curr_size -= need;
        System.out.println("已经消费了 " + need + " 个产品,现仓储量为 " + curr_size);

        //唤醒在此对象监视器上等待的所有线程
        notifyAll();
    }
}
