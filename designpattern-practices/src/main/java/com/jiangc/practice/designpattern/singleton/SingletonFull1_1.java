package com.jiangc.practice.designpattern.singleton;

public class SingletonFull1_1 {

	//私有化静态实例
	private static SingletonFull1_1 instance = null;
	
	/**
	 * 首先私有化构造函数
	 */
	private SingletonFull1_1(){
		
	}
	
	//获取实例的方法
	
	/**
	 * 对getInstance加锁synchronized绝对线程安全,但效率不高,实际上已经退化了串行 也就是每次获取实例都需要加锁 效率不高 已经变成串行
	 * 
	 * 对性能不敏感的场景下可以使用
	 * 
	 * @return
	 */
	public synchronized SingletonFull1_1 getInstance(){
		if(instance == null){
			instance = new SingletonFull1_1();
		}
		return instance;
	}
	
}
