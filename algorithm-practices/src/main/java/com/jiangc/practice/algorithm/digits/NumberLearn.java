package com.jiangc.practice.algorithm.digits;


import java.math.BigDecimal;

public class NumberLearn {
	
	public static void main(String[] args) {
		test_bigdecimal();
	}
	
	/**
	 * bigdecimal一定要指定精度
	 */
	public static void test_bigdecimal(){

		BigDecimal a = new BigDecimal("5.5");
		BigDecimal a1 = new BigDecimal("5.50");
		BigDecimal b = new BigDecimal("1.2");
		BigDecimal c = null;
		c = a.add(b);      //c = a + b
		System.out.println(c);//6.7
		c = a.subtract(b); //c = a - b
		System.out.println(c);//4.3
		c = a.multiply(b); //c = a * b
		System.out.println(c);//6.60
		c = a.divide(b,3,BigDecimal.ROUND_HALF_UP);   //c = a / b
		System.out.println(c);//Exception in thread "main" java.lang.ArithmeticException: Non-terminating decimal expansion;
		//c = a.divide(b);   //c = a / b
		//System.out.println(c);//Exception in thread "main" java.lang.ArithmeticException: Non-terminating decimal expansion;
		
		System.out.println(new BigDecimal(0.333));//0.333000000000000018207657603852567262947559356689453125
		System.out.println(new BigDecimal("0.333"));//0.333
		System.out.println(a == a1);
		System.out.println(a.equals(a1));
		System.out.println(a.compareTo(a1));//0 
		System.out.println(a.compareTo(b));//1
	}
	
	/**
	 * 强转数字的数据类型结果是错误的
	 */
	public static void test_int(){
		System.out.println((int)2.9);//2
		System.out.println((int)(29.0 * 0.01 * 100));//28
		System.out.println(29.0 * 0.01 * 100);//28.999999999999996
		
	}
	
	/**
	 * double 类型相加时会出现小数点后很多数字
	 */
	public static void test_double() {
		double x = 0;
		for (int i = 0; i < 50; i++) {
			x += 0.1;
			System.out.println("i: "+i+" x: "+x);
		}
		System.out.println("last number is "+x);
	}
}
