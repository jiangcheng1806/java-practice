package com.jiangc.practice.designpattern.factory.abstractfactory_level3;

/**
 * 
 * 发及时信息，则只需做一个实现类，实现Sender接口，同时做一个工厂类，实现Provider接口，就OK了，无需去改动现成的代码。这样做，拓展性较好！
 * 
 * 抽象工厂模式的便利性体现在静态工厂模式在需要生产新的类型的对象的时候就需要对工厂方法进行改写，工作量大，操作麻烦，使用抽象方法之后多了一个抽象工厂的父类，需要创建新的对象的时候只需要创建一个继承抽象工厂父类的工厂就可以了
 * 
 * @author Chan Kiang
 *
 */
public class Test {

	public static void main(String[] args) {
		Provider provider = new SendMailFactory();
		Sender sender = provider.produce();
		sender.send();
	}
}
