package com.jiangc.practice.designpattern.factory.multifactory_level2;

import com.jiangc.practice.designpattern.factory.commonfactory_level1.MailSender;
import com.jiangc.practice.designpattern.factory.commonfactory_level1.Sender;
import com.jiangc.practice.designpattern.factory.commonfactory_level1.SmsSender;

/**
 * 
 * 对普通工厂方法模式的改进，在普通工厂方法模式中，如果传递的字符串出错，则不能正确创建对象，而多个工厂方法模式是提供多个工厂方法，分别创建对象
 * 
 * 
 * @author Chan Kiang
 *
 */
public class SendFactory {

	public Sender produceMail(){
		return new MailSender();
	}
	
	public Sender produceSms(){
		return new SmsSender();
	}
}
