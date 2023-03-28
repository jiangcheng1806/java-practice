package com.jiangc.practice.designpattern.singleton;

/**
 * 
 * Holder模式
 * 
 * @author Chan Kiang
 *
 */
public class SingletonHolder {

	//私有化构造函数
	private SingletonHolder(){
		
	}
	
	//私有化静态类,在这个静态持有类里使用饿汉式
	private static class SingletonFactory{
		private static final SingletonHolder instance = new SingletonHolder();
		private SingletonFactory(){
			
		}
	}
	
	/**
	 * 
	 * 相对于饿汉模式，Holder模式仅增加了一个静态内部类的成本
	 * 
	 * @return
	 */
	public synchronized SingletonHolder getInstance(){
		return SingletonFactory.instance;
	}
	
}
