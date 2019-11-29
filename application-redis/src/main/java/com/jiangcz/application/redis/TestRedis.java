package com.jiangcz.application.redis;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

public class TestRedis {

    public void testSentinel() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMaxWaitMillis(2000);

        String masterName = "mymaster";
        Set<String> sentinelSet = new HashSet<>();
        sentinelSet.add("49.234.227.194:26379");
        sentinelSet.add("49.234.227.194:26380");
        sentinelSet.add("49.234.227.194:26381");
        JedisSentinelPool pool = new JedisSentinelPool(masterName, sentinelSet, "123456");
        //JedisSentinelPool pool = new JedisSentinelPool(masterName, sentinelSet,config);
// 获取master的主机和端口
        HostAndPort currentHostMaster = pool.getCurrentHostMaster();

        System.out.println(currentHostMaster.getHost() + "--" + currentHostMaster.getPort());
        Jedis jedis = null;
        try {
            jedis = pool.getResource();//连接不上的原因是因为 sentinel 设置的 master 的ip是 内网的ip 无法连接 改成外网的ip 就可以访问了
            //jedis.auth("123456");
            jedis.set("hello", "world");
            System.out.println(jedis.get("hello"));
            jedis.set("key", "aaa");
            while (true) {

                try {
                    System.out.println(jedis.getClient().getHost() + ":" + jedis.getClient().getPort() + "@" + jedis.get("key"));
                } catch (Exception e) {
                    System.out.println("getConntion  error,waiting  5s,will try again..." + e.getMessage());
                    Thread.sleep(5000);
                    try {
                        jedis = pool.getResource();
                    } catch (Exception e1) {
                        System.out.println("getResource  error2,waiting  more,will try again..." + e.getMessage());
                    }
                }
                Thread.sleep(2000);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        pool.close();
    }


    public void testJedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxWaitMillis(10);
        JedisPool pool = new JedisPool(config, "49.234.227.194", 6379);
        //Jedis jedis = new Jedis("49.234.227.194",6379);//直接连接可能不成功 因为redis 可能加校验了
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.auth("123456");
            jedis.set("hello", "world");
            String value = jedis.get("hello");
            System.out.println(value);
            jedis.del("hello");
            System.out.println(jedis.get("hello"));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            if (pool != null) {
                pool.close();
            }
        }
    }


    public void testJedis() {
        Jedis jedis = new Jedis("49.234.227.194", 6379);//直接连接可能不成功 因为redis 可能加校验了
        jedis.auth("123456");
        jedis.set("hello", "world");
        String value = jedis.get("hello");
        System.out.println(value);
        jedis.del("hello");
        System.out.println(jedis.get("hello"));
        if (jedis != null) {
            jedis.close();
        }
    }


}
