package com.jiangcz.application.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Redis 分布式锁，模板设计模式，封装锁的获得和释放逻辑
 * 
 * @author 李宁，孙钰佳
 * 
 */
public class RedisLockTemplate {
	
	@Autowired
	private IdGenerator idGenerator;

	@Autowired
	private RedisTemplate<String,Object> redisTemplate;

	
	/**
	 * 重试等待次数
	 */
	private int retryTimes=3;

	/**
	 * 重试等待时间
	 */
	private int retryAwait=300;
	
	private int lockTimeout=1000 * 60;
	

	public RedisLockTemplate(IdGenerator idGenerator, RedisTemplate<String,Object> redisTemplate){
		this.idGenerator = idGenerator;
		this.redisTemplate = redisTemplate;
	}

	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

	public void setRedisTemplate(RedisTemplate<String,Object> redisTemplate){
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 分布式锁执行器
	 * @param key
	 * @param redisLockCallback
     * @return
     */
	public Object execute(String key, RedisLockCallback redisLockCallback) {
		String uuid = idGenerator.generatIdByIncr("RedisLockIncr")+"";
		try {
			long flag = loopForLock(key,uuid);
			if(flag ==1)
			return redisLockCallback.doInRedisLock();
			else return 0;
		} finally {
			getset(key, uuid);
		}
	}

	/**
	 * key锁获取,uuid用于防止误删
	 * @param uuid
     * @return
     */
	private long getRedisLock(String key, String uuid) {
		return setnxpx(String.valueOf(key), lockTimeout, uuid);
	}

	/**
	 * 可重入获取锁
	 * @param uuid
     * @return
     */
	private long loopForLock(String key, String uuid){
		long getlockstate = 0;
		getlockstate = getRedisLock(key,uuid);
		if(getlockstate ==0){
			ReentrantLock pauseLock = new ReentrantLock();
			Condition unpaused = pauseLock.newCondition();
			try {
				pauseLock.lock();
				for (int i =0;getlockstate == 0&i<retryTimes;i++){
//					System.out.println("重入锁："+i);
					getlockstate = getRedisLock(key,uuid);
					unpaused.await(retryAwait, TimeUnit.MILLISECONDS);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				pauseLock.unlock();
			}
		}
		return getlockstate;
	}
	/**
	 * 原子性操作
	 * @param key
	 * @param value
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private Long getset(String key, String value) {
		String luaScript = ""
				+ "\r\nlocal val = redis.call('GET', '" + key + "');"
				+ "\r\nlocal ret= 0;"
				+ "\r\nif val == '" + value + "' then"
				+ "\r\nret =redis.call('DEL','" + key + "');"
				+ "\r\nend"
				+ "\r\nreturn ret";
		List<String> keys = new ArrayList<String>();
		RedisScript<Long> script = new DefaultRedisScript<Long>(
				luaScript, Long.class);


		Long ret = redisTemplate
				.execute(script, keys, new Object[]{});
		return ret;
	}
	
	
	/**
	 * 原子性操作
	 * @param key
	 * @param millseconds(毫秒)
	 * @param value
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private Long setnxpx(String key, int millseconds, String value) {
		String luaScript = ""
				+ "\r\nlocal ret = tonumber(redis.call('SETNX', '" + key + "','" + value + "'));"
				+ "\r\nredis.call('PEXPIRE','" + key + "','" + millseconds + "');"
				+ "\r\nreturn ret";

		List<String> keys = new ArrayList<String>();
		RedisScript<Long> script = new DefaultRedisScript<Long>(
				luaScript, Long.class);
		Long ret = redisTemplate
				.execute(script, keys, new Object[]{});

		return ret;

	}

	public int getRetryTimes() {
		return retryTimes;
	}
	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}
	public int getRetryAwait() {
		return retryAwait;
	}
	public void setRetryAwait(int retryAwait) {
		this.retryAwait = retryAwait;
	}
	public int getLockTimeout() {
		return lockTimeout;
	}
	public void setLockTimeout(int lockTimeout) {
		this.lockTimeout = lockTimeout;
	}

}
