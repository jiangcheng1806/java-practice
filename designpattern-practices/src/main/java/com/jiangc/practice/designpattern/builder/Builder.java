package com.jiangc.practice.designpattern.builder;

import com.jiangc.practice.designpattern.factory.commonfactory_level1.MailSender;
import com.jiangc.practice.designpattern.factory.commonfactory_level1.Sender;
import com.jiangc.practice.designpattern.factory.commonfactory_level1.SmsSender;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式
 * 建造者模式则是将各种产品集中起来进行管理，用来创建复合对象，所谓复合对象就是指某个类具有不同的属性，其实建造者模式就是前面抽象工厂模式和最后的Test结合起来得到的。
 * @author Chan Kiang
 *
 */
public class Builder {

	private List<Sender> list = new ArrayList<Sender>();
	
	public void produceMailSender(int count){
		for (int i = 0; i < count; i++) {
			list.add(new MailSender());
		}
	}
	
	public void produceSmsSender(int count){
		for (int i = 0; i < count; i++) {
			list.add(new SmsSender());
		}
	}
	
}
