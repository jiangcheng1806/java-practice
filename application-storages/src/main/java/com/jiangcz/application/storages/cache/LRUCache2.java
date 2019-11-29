package com.jiangcz.application.storages.cache;

        import java.util.LinkedHashMap;
        import java.util.Map;

/**
 * 类名称：LRUCache2<br>
 * 类描述：<br>
 * 创建时间：2018年09月13日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class LRUCache2<K,V> extends LinkedHashMap<K,V> {

    private final int MAX_CACHE_SIZE;
    public LRUCache2(int cacheSize){

        //LinkedHashMap的一个构造函数，当参数accessOrder为true时，即会按照访问顺序排序，最近访问的放在最前，最早访问的放在后面
        super((int) Math.ceil(cacheSize / 0.75) + 1,0.75f,true);
        MAX_CACHE_SIZE = cacheSize;
    }

    //LinkedHashMap自带的判断是否删除最老的元素方法，默认返回false，即不删除老数据 //我们要做的就是重写这个方法，当满足一定条件时删除老数据
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_CACHE_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K,V> entry : entrySet()){
            sb.append(String.format("%s:%s",entry.getKey(),entry.getValue()));
        }
        return sb.toString();
    }
}
