package com.jiangc.practice.storages.cache;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类名称：MyCache<br>
 * 类描述：<br>
 * 创建时间：2018年05月22日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class MyCache{

    //日志记录
    private static final Logger logger = Logger.getLogger(MyCache.class);

    //单例
    private static MyCache myCache;

    private static Map<Object,  Object> cacheItems;
    private static Map<Object,Object> cacheConfs;

    //单例私有化构造函数
    private MyCache(){
        cacheItems = new ConcurrentHashMap<>();
        cacheConfs = new ConcurrentHashMap<>();
    }

    //获取唯一实例
    public static MyCache getInstance(){
        if (myCache == null){
            synchronized (MyCache.class){
                if (myCache == null){
                    myCache = new MyCache();
                }
            }
        }
        return myCache;
    }

    //清空cache
    public void clearCacheItems(){
        cacheItems.clear();
    }



    //刷新cache
    public void refresh(){
        logger.info("Before refreshing Cache size = "+ myCache.getSize());
        //清空缓存
        cacheItems.clear();
        //重新查库
        Map<String,String> mockMap = getMockMap();
        //遍历map
        Set<String> set = mockMap.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()){
            Object key = it.next();
            Object value = mockMap.get(key);
            if (key != null && value != null){
                cacheItems.put(key,value);
            }
        }
        logger.info("After the refresh Cache size = "+ myCache.getSize());
    }

    //获取cache长度
    public int getSize(){
        return cacheItems.size();
    }

    //获取指定cache信息
    public synchronized Object get(Object key){
        if (cacheItems.containsKey(key)){
            return cacheItems.get(key);
        }
        return null;
    }

    //没有则插入
    public synchronized Object retrieveLong(Object key){
        return cacheItems.computeIfAbsent(key,this::computeLong);
    }

    //模拟代价很高的计算
    public Object computeLong(Object value){
        return (Long) value*(Long) value;
    }

    //存放cache信息,默认永久缓存
    public static synchronized void add(Object key,Object value){
        if (!cacheItems.containsKey(key)){
            cacheItems.put(key,value);
        }
        if (!cacheConfs.containsKey(key)){
            cacheConfs.put(key,new CacheConf(true));
        }
    }

    //存放cache信息
    public void add(Object key,Object value,CacheConf cacheConf){
        if (!cacheItems.containsKey(key)){
            cacheItems.put(key,value);
        }
        if (!cacheConfs.containsKey(key)){
            cacheConfs.put(key,cacheConf);
        } else {
            logger.info("配置已存在不新增配置");
        }
    }

    //删除cache信息
    public boolean remove(Object key){
        boolean flag = false;
        try {
            cacheConfs.remove(key);
            cacheItems.remove(key);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    //获取所有cache信息
    public Map<Object,Object> getAll(){
        return cacheItems;
    }

    //设置cache内容
    public void setAll(Map<Object, Object> cacheItems) {
        cacheItems = cacheItems;
    }

    //mock一个map
    public Map<String,String> getMockMap(){
        Map<String,String> mockMap = new ConcurrentHashMap<>(12);
        for (int i = 0; i < 12; i++) {
            mockMap.put("key"+i,"value"+i*i);
        }
        return mockMap;
    }

    private static class ClearCache extends Thread{
        @Override
        public void run() {
            while (true){
                Set tempSet = new HashSet();
                Set keySet = cacheConfs.keySet();
                Iterator keyIt = keySet.iterator();
                while (keyIt.hasNext()){
                    Object key = keyIt.next();
                    CacheConf cacheConf = (CacheConf) cacheConfs.get(key);
                    if (!cacheConf.isForever()){
                        if (System.currentTimeMillis() - cacheConf.getBeginTime() >= cacheConf.getDurableTime()*1000){
                            tempSet.add(key);
                        }
                    }
                }
                //清除
                Iterator tIt = tempSet.iterator();
                while (tIt.hasNext()){
                    Object key = tIt.next();
                    cacheItems.remove(key);
                    cacheConfs.remove(key);
                }
                logger.info("now thread================>"
                        + myCache.getSize());
                // 休息
                try {
                    Thread.sleep(60 * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MyCache myCache1 = MyCache.getInstance();
        /*myCache.refresh();
        Map map = myCache.getCacheItems();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()){
            Object key = it.next();
            System.out.println("key:"+key+",value:"+map.get(key));
        }*/
        CacheConf cacheConf = new CacheConf(true);
        cacheConf.setBeginTime(System.currentTimeMillis());
        cacheConf.setDurableTime(60);
        myCache.add("mykey","myvalue",cacheConf);

        MyCache myCache2 = MyCache.getInstance();
        System.out.println("num:"+myCache2.getSize());
        System.out.println("aa"+myCache2.retrieveLong(112L));
    }
}

/**
 * 配置类
 */
class CacheConf implements Serializable {

    private long beginTime;//缓存开始时间
    private boolean isForever;//是否永久
    private long durableTime;//持续时间

    public CacheConf(boolean isForever) {
        this.isForever = isForever;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public boolean isForever() {
        return isForever;
    }

    public void setForever(boolean forever) {
        isForever = forever;
    }

    public long getDurableTime() {
        return durableTime;
    }

    public void setDurableTime(long durableTime) {
        this.durableTime = durableTime;
    }
}