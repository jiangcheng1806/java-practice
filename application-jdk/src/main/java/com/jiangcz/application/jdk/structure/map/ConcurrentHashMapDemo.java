package com.jiangcz.application.jdk.structure.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类名称：ConcurrentHashMapDemo<br>
 * 类描述：<br>
 * 创建时间：2019年04月22日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class ConcurrentHashMapDemo {

    private Map<Integer,Integer> cache = new ConcurrentHashMap<>(15);

    public static void main(String[] args) {
        ConcurrentHashMapDemo demo = new ConcurrentHashMapDemo();
        System.out.println(demo.fibonaacci(80));
    }


    public int fibonaacci(Integer i){
        if (i == 0||i == 1){
            return i;
        }

        return cache.computeIfAbsent(i,(key) -> {
            System.out.println("fibonacci :" + key);
            return fibonaacci(key - 1) + fibonaacci(key -2);
        });
    }
}
