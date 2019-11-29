package com.jiangcz.application.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 乐观锁实现秒杀
 */
public class Sha {
    public static void main(String[] args) {
        String redisKey = "redisTest";
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        try {
            Jedis jedis = new Jedis("49.234.227.194", 6379);
            jedis.auth("123456");
            jedis.set(redisKey,"0");
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10000; i++) {
            executorService.execute(() -> {
                Jedis jedis = new Jedis("49.234.227.194", 6379);
                try {
                    jedis.auth("123456");
                    jedis.watch(redisKey);
                    String redisValue = jedis.get(redisKey);
                    int valInteger = Integer.valueOf(redisValue);
                    String userInfo = UUID.randomUUID().toString();
                    if (valInteger<20){
                        Transaction tx = jedis.multi();
                        tx.incr(redisKey);
                        List list = tx.exec();
                        if (list != null){
                            System.out.println("用户："+userInfo+"，秒杀成功！当前成功人数："+(valInteger+1));
                        } else {
                            System.out.println("用户："+userInfo+"，秒杀失败");
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } finally {
                    jedis.close();
                }
            });
        }

        executorService.shutdown();
    }
}
