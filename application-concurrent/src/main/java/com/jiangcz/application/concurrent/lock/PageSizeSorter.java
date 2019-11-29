package com.jiangcz.application.concurrent.lock;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * 类名称：PageSizeSorter<br>
 * 类描述：倒数闸门  CountDownLatch<br>
 * 创建时间：2018年08月01日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class PageSizeSorter {
    // 并发性能远远优于HashTable的 Map实现，hashTable做任何操作都需要获得锁，同一时间只有有个线程能使用，而ConcurrentHashMap是分段加锁，不同线程访问不同的数据段，完全不受影响，忘记HashTable吧。
    private static final ConcurrentHashMap<String,Integer> sizeMap = new ConcurrentHashMap<>();

    private static class GetSizeWorker implements Runnable {
        private final String urlString;
        private final CountDownLatch signal;
        public GetSizeWorker (String urlString, CountDownLatch signal){
            this.urlString = urlString;
            this.signal = signal;
        }

        @Override
        public void run() {
            try {
                InputStream is = new URL(urlString).openStream();
                int size = IOUtils.toByteArray(is).length;
                sizeMap.put(urlString,size);
            } catch (IOException e) {
                sizeMap.put(urlString,-1);
            } finally {
                signal.countDown();//完成一个任务 ， 任务数-1
            }
        }
    }

    private void sort(){
        List<Map.Entry<String,Integer>> list = new ArrayList(sizeMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return Integer.compare(o2.getValue(),o1.getValue());
            }
        });
    }

    public void sortPageSize(Collection<String> urls) throws InterruptedException {
        CountDownLatch sortSignal = new CountDownLatch(urls.size());
        for (String url : urls){
            new Thread(new GetSizeWorker(url,sortSignal)).start();
        }
        sortSignal.await();//主线程在这里等待，任务数归0，则继续执行
        sort();
    }

}
