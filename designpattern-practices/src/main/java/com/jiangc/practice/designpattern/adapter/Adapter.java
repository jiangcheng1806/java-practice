package com.jiangc.practice.designpattern.adapter;




/**
 * 对象的适配器模式
 * @author jiangcheng-wb
 *
 */
public class Adapter extends Source implements TargetTable {

	@Override
	public void method2() {
		// TODO Auto-generated method stub
		System.out.println("This is the targettable method!");
	}

}
