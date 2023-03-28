package com.jiangc.practice.designpattern.factory.commonfactory_level1;

/**
 * 
 * 这里两个Sender实现了同一接口，创建这些类可以使用工厂模式进行生产
 * 
 * 
 * 
 * @author Chan Kiang
 *
 */
public class FactoryTest {

	public static void main(String[] args) {
		
		SendFactory factory = new SendFactory();
		Sender sender = factory.produce("sms");
		sender.send();
	}
}
