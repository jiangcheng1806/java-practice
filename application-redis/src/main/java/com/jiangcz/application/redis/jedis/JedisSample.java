package com.jiangcz.application.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisSample {

    /**
     * 通过pool来访问
     */
    public static void main1() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxWaitMillis(10);
        JedisPool pool = new JedisPool(config,"49.234.227.194",6379);
        //Jedis jedis = new Jedis("49.234.227.194",6379);//直接连接可能不成功 因为redis 可能加校验了
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.auth("123456");
            jedis.set("hello","world");
            String value = jedis.get("hello");
            System.out.println(value);
            jedis.del("hello");
            System.out.println(jedis.get("hello"));
        } finally {
            if (jedis != null){
                jedis.close();
            }
            if (pool != null){
                jedis.close();
            }
        }
    }

    /**
     * 普通访问
     * @param args
     */
    public static void main(String[] args) {
        Jedis jedis = new Jedis("49.234.227.194",6379);//直接连接可能不成功 因为redis 可能加校验了
        jedis.auth("123456");
        jedis.set("hello","world");
        String value = jedis.get("hello");
        System.out.println(value);
        jedis.del("hello");
        System.out.println(jedis.get("hello"));
        if (jedis != null){
            jedis.close();
        }
    }
}
