package com.jiangc.practice.designpattern.singleton;

/**
 * 
 * 单例模式
 * 
 * 饱汉模式,基础饱汉模式在单线程下优先使用
 * 
 * 可读性好,启动速度快节省资源
 * 
 * 
 * @author Chan Kiang
 *
 */
public class SingletonFull1_0 {

	//私有化本类型实例
	private static SingletonFull1_0 instance = null;
	
	/**
	 * 构造函数私有化
	 */
	private SingletonFull1_0(){
		
	}
	
	public SingletonFull1_0 getInstance(){
		
		if(instance == null){
			instance = new SingletonFull1_0();
		}
		
		return instance;
	}
}
