package com.jiangc.practice.designpattern.singleton;


/**
 * 
 * 其实仍然是DCL的变种
 * @author Chan Kiang
 *
 */
public class SingletonFull1_4 {

	//私有化静态实例
	
	/**
	 * 
	 * 
	 * 
	 */
	private static volatile SingletonFull1_4 instance = null;
	
	/**
	 * 首先私有化构造函数
	 */
	private SingletonFull1_4(){
		
	}
	
	/**
	 * 
	 * 将创建和getInstance()分开，单独为创建加synchronized关键字
	 * 
	 */
	private static synchronized void syncInit(){
		if(instance == null){
			instance = new SingletonFull1_4();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public SingletonFull1_4 getInstance(){
		if(instance == null){
			syncInit();
		}
		return instance;
	}
	
}
