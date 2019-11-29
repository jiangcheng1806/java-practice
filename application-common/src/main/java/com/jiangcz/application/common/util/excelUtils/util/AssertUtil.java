package com.jiangcz.application.common.util.excelUtils.export;

/**
 * 断言工具类
 * 用作方法参数校验
 * @author chennina
 *
 */
public class AssertUtil {

	private AssertUtil(){	
	}
	
	/**
	 * 判断断言为真
	 * @param expression 断言
	 * @param errorMsg 为假时出错信息
	 * @return
	 */
	public static boolean isTrue(boolean expression, String errorMsg ){
		if(!expression){
			throw new IllegalArgumentException(errorMsg);
			
		}
		return true;
	}
}
