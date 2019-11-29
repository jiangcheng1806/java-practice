package com.jiangcz.application.common.util.excelUtils.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * 参数及值检查工具
 * @author chennina
 *
 */
public class ValueCheckUtil {
	
	
	/**
	 * 判断集合是否空
	 * @param param 集合
	 * @return TRUE ： 空; FALSE:非空
	 */
	public static  <T extends Object>   boolean isNullorZeroLength(Collection<T> param){
		if(param==null||param.size()==0){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断集合是否空
	 * @param param 集合
	 * @return TRUE ： 空; FALSE:非空
	 */
	public static  <K ,V extends Object>  boolean isNullorZeroLength(Map<K,V> param){
		if(param==null||param.size()==0){
			return true;
		}
		return false;
	}

	
	/**
	 * 判断数组非空
	 * @param objs
	 * @return
	 */
	public static boolean isNullorZeroLength(Object[] objs){
		if(objs!=null && objs.length!=0){
			return false;
		}
		return true;
	}
	
	/**
	 * 判断对象是否相等
	 * @param left
	 * @param right
	 * @return
	 */
	public static  boolean isObjEqual(Object left , Object right){
		if(left ==null && right ==null){
			return true;
		}else if(left!=null && right !=null ){
			return left.equals(right);
		}
		return false;
	}
	

	/**
	 * 数字是否小于0
	 * @param value
	 * @return TRUE : 小于0
	 */
	public static boolean isLessThanZero(BigDecimal value){
		if(value==null){
			return false;
		}
		if(value.signum()<0){
			return true;
		}
		return false;
	}
	


}
