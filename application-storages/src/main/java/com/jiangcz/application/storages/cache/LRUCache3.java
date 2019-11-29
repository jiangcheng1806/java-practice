package com.jiangcz.application.storages.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 类名称：LRUCache3<br>
 * 类描述：<br>
 * 创建时间：2018年09月13日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class LRUCache3<K,V> {
    private final int MAX_CACHE_SIZE;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    LinkedHashMap<K,V> map;
    public LRUCache3(int cacheSize){
        MAX_CACHE_SIZE = cacheSize;
        //根据cacheSize和加载因子计算hashmap的capactiy，+1确保当达到cacheSize上限时不会触发hashmap的扩容，
        int capacity = (int) (Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTOR) + 1);
        map = new LinkedHashMap<K,V>(capacity,DEFAULT_LOAD_FACTOR,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > MAX_CACHE_SIZE;
            }
        };
    }

    public synchronized void put(K key,V value){
        map.put(key, value);
    }

    public synchronized V get(K key){
        return map.get(key);
    }

}
