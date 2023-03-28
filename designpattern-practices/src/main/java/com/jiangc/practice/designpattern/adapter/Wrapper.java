package com.jiangc.practice.designpattern.adapter;

public class Wrapper implements TargetTable {

	private Source source;
	
	
	public Wrapper(Source source){
		super();
		this.source = source;
	}
	
	@Override
	public void method1() {
		// TODO Auto-generated method stub
		source.method1();
	}

	@Override
	public void method2() {
		// TODO Auto-generated method stub
		System.out.println("This is the targettable method!");
	}

}
