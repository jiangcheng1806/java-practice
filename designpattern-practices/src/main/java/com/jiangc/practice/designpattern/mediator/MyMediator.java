package com.jiangc.practice.designpattern.mediator;



/**
 *
 * 中介者模式
 * 
 * User类统一接口，User1和User2分别是不同的对象，二者之间有关联，如果不采用中介者模式，则需要二者相互持有引用，这样二者的耦合度很高，
 * 为了解耦，引入了Mediator类，提供统一接口，MyMediator为其实现类，里面持有User1和User2的实例，用来实现对User1和User2的控制。
 * 这样User1和User2两个对象相互独立，他们只需要保持好和Mediator之间的关系就行，剩下的全由MyMediator类来维护！
 * 
 * 
 * 
 * 
 * @author jiangcheng-wb
 *
 */
public class MyMediator implements Mediator {

	private User user1;
	
	private User user2;
	
	public User getUser1(){
		return user1;
	}
	
	public User getUser2(){
		return user2;
	}
	
	@Override
    public void createMediator() {
		// TODO Auto-generated method stub
		user1 = new User1(this);
		
		user2 = new User2(this);
	}

	@Override
    public void workAll() {
		// TODO Auto-generated method stub
		user1.work();
		
		user2.work();
	}

}
