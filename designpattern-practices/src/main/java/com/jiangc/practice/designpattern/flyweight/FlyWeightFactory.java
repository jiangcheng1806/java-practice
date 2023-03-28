package com.jiangc.practice.designpattern.flyweight;

import java.util.HashMap;

/**
 *
 *
 *
 * 享元模式
 *
 *
 * 类名称：FlyWeightFactory<br>
 * 类描述：<br>
 * 创建时间：2018年08月28日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public abstract class FlyWeightFactory {
    //定义一个池容器
    private static HashMap<String,Flyweight> pool = new HashMap<>();

    //享元工厂
    public static Flyweight getFlyweight(String extinct){
        //需要返回的对象
        Flyweight flyweight = null;
        //在池中没有该对象
        if (pool.containsKey(extinct)){
            flyweight = pool.get(extinct);
        } else {
            //根据外部状态创建享元对象
            flyweight = new ConcretFlyweight(extinct);
            //放置到池中
            pool.put(extinct,flyweight);
        }
        return flyweight;
    }

}
abstract class Flyweight {}
class ConcretFlyweight extends Flyweight{
    String extinct;
    ConcretFlyweight(String extinct){
        this.extinct = extinct;
    }
}
