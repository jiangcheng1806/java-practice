package com.jiangc.practice.designpattern.singleton;

public class SingletonFull1_3 {

	//私有化静态实例
	
	/**
	 * 
	 * 
	 * 相对SingletonFull1_2加了volatile关键字
	 * 
	 * volatile作用：1禁用存贮器存储变量2强制按循序执行不进行指令重排
	 * 实现DCL2.0
	 * 
	 * 
	 */
	private static volatile SingletonFull1_3 instance = null;
	
	/**
	 * 首先私有化构造函数
	 */
	private SingletonFull1_3(){
		
	}
	
	//获取实例的方法
	
	/**
	 * 
	 * DCL(Double Check Lock)
	 * 
	 * @return
	 */
	public SingletonFull1_3 getInstance(){
		if(instance == null){
			synchronized(SingletonFull1_3.class){
				if(instance == null){
					instance = new SingletonFull1_3();
				}
			}
		}
		return instance;
	}
	
}
