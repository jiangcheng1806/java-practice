package com.jiangc.practice.designpattern.strategy;





/**
 *
 * 策略模式
 *
 * 策略模式定义了一系列算法，并将每个算法封装起来，使他们可以相互替换，且算法的变化不会影响到使用算法的客户。
 * 需要设计一个接口，为一系列实现类提供统一的方法，多个实现类实现该接口，设计一个抽象类（可有可无，属于辅助类），提供辅助函数
 * 
 * 
 * AbstractCalculator是辅助函数用来做split之类的作用
 * ICalcultor定义统一的接口,实现这个接口的各个算法的方法可以互相替换
 * 
 * 
 * 
 * 策略模式的决定权在用户，系统本身提供不同算法的实现，新增或者删除算法，对各种算法做封装。
 * 因此，策略模式多用在算法决策系统中，外部用户只需要决定用哪个算法即可
 * 
 * 
 * 
 * @author jiangcheng-wb
 *
 */
public class StrategyTest {

	public static void main(String[] args) {
		
		String exp = "2+8";
		
		ICalculator cal = new Plus();
		
		int result = cal.calculate(exp);
		
		System.out.println(result);
	}
}
