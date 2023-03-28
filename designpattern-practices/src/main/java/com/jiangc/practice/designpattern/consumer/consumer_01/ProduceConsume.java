package com.jiangc.practice.designpattern.consumer.consumer_01;

/**
 * 类名称：ProduceConsume<br>
 * 类描述：wait and notify
 *
 *
 *
 * 生产者生产数据到缓冲区中，消费者从缓冲区中取数据。
 *
 * 如果缓冲区已经满了，则生产者线程阻塞；
 *
 * 如果缓冲区为空，那么消费者线程阻塞。
 *
 * 使用 wait notify
 *
 * <br>
 * 创建时间：2018年08月28日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class ProduceConsume {
    public static void main(String[] args) {



    }
}

/**
 * 公共资源类
 */
class Resource { //重要

    //当前资源数量
    private int num = 0;
    //资源池中允许存放的资源数目
    private int size = 10;

    /**
     * 从资源池中取走资源
     */
    public synchronized void remove(){
        if (num > 0){
            num--;
            System.out.println("消费者 " + Thread.currentThread().getName() + " 消耗一件资源，当前线程池有 " + num + " 个" );
            notifyAll();
        } else {
            try {
                //如果没有资源，则消费者进入等待状态
                wait();
                System.out.println("消费者 " + Thread.currentThread().getName() + " 线程进入等待状态");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 向资源池中添加资源
     */
    public synchronized void add(){
        if (num < size){
            num++;
            System.out.println("生产者 " + Thread.currentThread().getName() + " 生产一件资源,当前资源池有" + num + "个");
            //通知等待的消费者
            notifyAll();
        } else {
            //如果当前资源池中有10件资源
            try {
                wait();
                System.out.println("生产者 " + Thread.currentThread().getName() + " 线程进入等待状态");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 消费者线程
     */
    class ConsumerThread extends Thread{
        private Resource resource;
        public ConsumerThread(Resource resource){
            this.resource = resource;
        }

        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resource.remove();
            }
        }
    }

    /**
     * 生产者线程
     */
    class ProducerThread extends Thread{
        private Resource resource;
        public ProducerThread(Resource resource){
            this.resource = resource;
        }

        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resource.add();
            }
        }
    }
}