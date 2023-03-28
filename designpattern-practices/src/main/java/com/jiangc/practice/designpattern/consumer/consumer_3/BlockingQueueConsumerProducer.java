package com.jiangc.practice.designpattern.consumer.consumer_3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 类名称：BlockingQueueConsumerProducer<br>
 * 类描述：使用阻塞队列BlockingQueue解决生产者消费者<br>
 * 创建时间：2018年08月29日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class BlockingQueueConsumerProducer {
    public static void main(String[] args) {
        Resource resource = new Resource();
        //生产者线程
        ProducerThread producer1 = new ProducerThread(resource);
        //多个消费者
        ConsumerThread consumer1 = new ConsumerThread(resource);
        ConsumerThread consumer2 = new ConsumerThread(resource);
        ConsumerThread consumer3 = new ConsumerThread(resource);

        producer1.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
    }
}

/**
 * 消费者线程
 */
class ConsumerThread extends Thread {
    private Resource resource;

    public ConsumerThread(Resource resource){
        this.resource = resource;
        //setName("消费者");
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep((long) (1000 * Math.random()));
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
class ProducerThread extends Thread {
    private Resource resource;
    public  ProducerThread(Resource resource){
        this.resource = resource;
        //setName("生产者");
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep((long) (1000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.add();
        }
    }
}

class Resource{
    private BlockingQueue resourceQueue = new LinkedBlockingDeque(10);
    /**
     * 向资源池中添加资源
     */
    public void add(){
        try{
            resourceQueue.put(1);
            System.out.println("生产者" + Thread.currentThread().getName()+ "生产一件资源," + "当前资源池有 " + resourceQueue.size() +" 个资源");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向资源池中移除资源
     */
    public void remove(){
        try{
            resourceQueue.take();
            System.out.println("消费者" + Thread.currentThread().getName() +"消耗一件资源," + "当前资源池有" + resourceQueue.size()+ "个资源");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}