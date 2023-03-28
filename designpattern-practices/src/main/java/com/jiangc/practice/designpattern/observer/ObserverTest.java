package com.jiangc.practice.designpattern.observer;

/**
 *
 * 观察者模式
 *
 * 对主题新增一个通知列表 通知列表中就是观察者了 主题对应的操作需要通知所有观察者
 *
 *
 *
 */
public class ObserverTest {

	public static void main(String[] args) {
		
		Subject sub = new MySubject();
		sub.add(new Observer1());
		sub.add(new Observer2());
		
		sub.operation();
	}
}
