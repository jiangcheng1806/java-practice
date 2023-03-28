package com.jiangc.practice.designpattern.proxy.staticproxy;

/**
 *
 * 这完全是静态代理的实现方式
 *
 *
 * 将被代理的类作为属性放在代理类里 然后直接调用代理类而不去调用被代理的原来的类
 *
 *
 *
 */
public class Proxy implements Sourceable {

	private Source source;
	
	public Proxy(){
		super();
		
		this.source = new Source();
	}
	
	@Override
	public void method() {
		// TODO Auto-generated method stub
		before();
		
		source.method();
		
		after();
	}

	
	public void before(){
		System.out.println("before proxy~!");
		
	}
	
	
	public void after(){
		System.out.println("after prixy");
	}
}
