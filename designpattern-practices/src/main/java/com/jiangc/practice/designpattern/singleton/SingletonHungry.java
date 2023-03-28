package com.jiangc.practice.designpattern.singleton;

/**
 * 
 * 单例模式
 * 
 * 饿汉模式,天生线程安全
 * 
 * 
 * @author Chan Kiang
 *
 */
public class SingletonHungry {

	/**
	 * 作为静态变量初始化
	 */
	private static final SingletonHungry instance = new SingletonHungry();
	
	/**
	 * 私有化构造函数
	 */
	private SingletonHungry(){
		
	}
	
	public SingletonHungry getInstance(){
		return instance;
	}
	
	
}
