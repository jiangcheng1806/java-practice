package com.jiangcz.application.common.util.excelUtils.util;




import com.jiangcz.application.common.util.excelUtils.exception.ExceptionUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EntityUtil {

	/**
	 * 设置属性
	 * 
	 * @param obj
	 * @param property
	 * @param
	 */
	public static  void setProperty(Object obj, String property, Object param,
			Class<?> paramType) {
		String prefix = "set" + property.toUpperCase().charAt(0);
		String temp = property.substring(1, property.length());
		String methodName = prefix + temp;
		Method m = null;
		try {
			m = obj.getClass().getMethod(methodName, paramType);
			m.invoke(obj, param);
		} catch (SecurityException e) {
			e.printStackTrace();
			ExceptionUtil.wrapperException(e);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			ExceptionUtil.wrapperException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			ExceptionUtil.wrapperException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			ExceptionUtil.wrapperException(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			ExceptionUtil.wrapperException(e);
		}

	}
	
	
	
	public static Object getProperty(Object obj, String property){
		String prefix = "get" + property.toUpperCase().charAt(0);
		String temp = property.substring(1, property.length());
		String methodName = prefix + temp;
		Method m = null;
		try {
			m = obj.getClass().getMethod(methodName);
			return m.invoke(obj);
		} catch (SecurityException e) {
			e.printStackTrace();
			ExceptionUtil.wrapperException(e);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			ExceptionUtil.wrapperException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			ExceptionUtil.wrapperException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			ExceptionUtil.wrapperException(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			ExceptionUtil.wrapperException(e);
		}

		return null;

	}
}
