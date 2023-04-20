package com.jiangc.practice.jdk.aqs;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphorTest {
    public static void main(String[] args) {
        // 假设餐厅有20张椅子
        Semaphore semaphore = new Semaphore(20 , true);
        Random random = new Random();
        // 10是假设单位时间的单子量
        for (int i=0; i<10; i++){
            Thread t = new Thread(){
                @Override
                public void run() {
                    try {
                        for (;;){
                            // count是这个单子的客人要占用多少把椅子
                            int count = random.nextInt(9) + 1;
                            System.out.println(getName() + " need " + count + " chairs.");
                            // 为客人安排椅子
                            semaphore.acquire(count);
                            System.out.println(getName() + " 进食，占用 " + count + " chairs.");
                            // 安排上了，假设了进食时间
                            sleep(1000);
                            System.out.println(getName() + " 离开 。");
                            // 空出来椅子
                            semaphore.release(count);
                        }
                    } catch (Exception e){
                    }
                }
            };
            t.setName("Thread --> " + i);
            t.start();
        }
    }
}
