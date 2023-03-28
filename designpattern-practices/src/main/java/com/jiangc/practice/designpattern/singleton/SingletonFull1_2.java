package com.jiangc.practice.designpattern.singleton;

public class SingletonFull1_2 {

	//私有化静态实例
	private static SingletonFull1_2 instance = null;
	
	/**
	 * 首先私有化构造函数
	 */
	private SingletonFull1_2(){
		
	}
	
	//获取实例的方法
	
	/**
	 * 
	 * 
	 * DCL(Double Check Lock)
	 * 
	 * 对性能不敏感的场景下可以使用
	 * 
	 * 懒加载+线程安全。可惜的是，正如注释中所说，DCL仍然是线程不安全的，由于指令重排序，你可能会得到“半个对象”。
	 * 
	 * 所谓指令重排序就是服务器多核情况下为了优化指令执行效率有时会打乱执行指令的步骤,
	 * 
	 * 这里jvm会先给instance分配空间但是暂不初始化
	 * 
	 * 别的线程检测到非空就开始取数据却取不到数据报错
	 * 
	 * 
	 * 
	 * @return
	 */
	public SingletonFull1_2 getInstance(){
		if(instance == null){
			synchronized(SingletonFull1_2.class){
				if(instance == null){
					instance = new SingletonFull1_2();
				}
			}
		}
		return instance;
	}
	
}
