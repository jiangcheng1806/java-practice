package com.jiangc.practice.designpattern.bridge;

public class MyBridge extends Bridge {

	@Override
    public void method(){
		getSource().method();
	}
}
