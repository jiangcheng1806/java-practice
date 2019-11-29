package com.jiangcz.application.storages.cache.guavacache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 类名称：CacheMap<br>
 * 类描述：<br>
 * 创建时间：2018年08月10日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */
@Slf4j
public class CacheMap {

    /**
     * 使用google guava缓存处理
     */
    private static Cache<String,Object> cache;

    static {
        cache = CacheBuilder.newBuilder().maximumSize(10000).expireAfterWrite(24,TimeUnit.HOURS).initialCapacity(10).removalListener((RemovalListener<String, Object>) rn -> {
            if (log.isInfoEnabled()){
                log.info("被移除缓存 {}:{}",rn.getKey(),rn.getValue());
            }
        }).build();
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public static Object get(String key){
        return StringUtils.isNoneEmpty(key)?cache.getIfPresent(key):null;
    }

    /**
     * 放入缓存
     * @param key
     * @param value
     */
    public static void put(String key,Object value){
        if (StringUtils.isNoneEmpty(key) && value != null){
            cache.put(key, value);
        }
    }

    /**
     * 移除缓存
     * @param key
     */
    public static void remove(String key){
        if (StringUtils.isNoneEmpty(key)){
            cache.invalidate(key);
        }
    }

    /**
     * 批量删除缓存
     * @param keys
     */
    public static void remove(List<String> keys){
        if (keys != null && keys.size() > 0){
            cache.invalidateAll(keys);
        }
    }
}
