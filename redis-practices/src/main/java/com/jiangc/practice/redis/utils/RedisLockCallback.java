package com.jiangc.practice.redis.utils;

/**
 * RedisTemplate的回调方法
 * @author 李宁
 *
 */
public interface RedisLockCallback {
	public Object doInRedisLock();
}
