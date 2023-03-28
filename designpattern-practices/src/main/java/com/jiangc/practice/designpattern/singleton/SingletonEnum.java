package com.jiangc.practice.designpattern.singleton;

/**
 * 
 * 
 * 枚举模式ThreadSafe
 * 
 * 可以在枚举型型单例上增加任何普通类可以完成的功能。
 * 要点在于枚举实例的初始化，可以理解为实例化了一个匿名内部类。
 * 为了更明显，我们在Singleton4_1中定义一个普通的私有成员变量，一个普通的公有成员方法，和一个公有的抽象成员方法
 * 
 * @author Chan Kiang
 *
 */
public enum SingletonEnum {

	
	SINGLETON("enum is the easiest single pattern,but is not the readable"){

		@Override
		public void testAbsTest() {
			// TODO Auto-generated method stub
			print();
			System.out.println("enum is ugly,but so flexible to make lot of trick");
		}
		
	};
	
	private String comment = null;
	SingletonEnum(String comment){
		this.comment = comment;
	}
	
	public void print(){
		System.out.println("comment is " + comment);
	}
	
	//要实现的方法
	abstract public void testAbsTest();
	
	/** 
	 * getInstance方法获取enum实例
	 * 
	 * @return
	 */
	public static SingletonEnum getInstance(){
		return SINGLETON;
	}
	
}
