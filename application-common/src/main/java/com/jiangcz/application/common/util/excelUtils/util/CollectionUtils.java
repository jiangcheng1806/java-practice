package com.jiangcz.application.common.util.excelUtils.util;



import com.jiangcz.application.common.util.excelUtils.exception.ExceptionUtil;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 集合工具类
 * @author chennina
 *
 */
public class CollectionUtils {

	/**
	 * 对象list 转换为map
	 * @param entities 对象集合
	 * @param methodName 对象属性Get方法，属性值唯一
	 * @return 对象Map集合 key为methodName对应属性值
	 */
	@SuppressWarnings("unchecked")
	public static <E extends Object, T extends Object> Map<E, T> hashMap(
			List<T> entities, String methodName) {
		Map<E, T> retMap = new HashMap<E, T>();
		if (ValueCheckUtil.isNullorZeroLength(entities)) {
			return retMap;
		}

		Class<T> clz = (Class<T>) entities.get(0).getClass();
		for (T entity : entities) {
			try {
				Method method = clz.getMethod(methodName);
				E keyValue = (E) method.invoke(entity);
				retMap.put(keyValue, entity);

			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				ExceptionUtil.wrapperException(e);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				ExceptionUtil.wrapperException(e);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				ExceptionUtil.wrapperException(e);
			}
		}

		return retMap;

	}

	/**
	 * 获取值集合
	 * @param entities 实体列表
	 * @param methodName 方法名称
	 * @return Set
	 */
	@SuppressWarnings("unchecked")
	public static <E extends Object, T extends Object> Set<E> getValueSet(
			List<T> entities, String methodName) {
		Set<E> ret = new HashSet<E>();
		if (ValueCheckUtil.isNullorZeroLength(entities)) {
			return ret;
		}

		Class<T> clz = (Class<T>) entities.get(0).getClass();
		for (T entity : entities) {
			try {
				Method method = clz.getMethod(methodName);
				E keyValue = (E) method.invoke(entity);
				ret.add(keyValue);

			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				ExceptionUtil.wrapperException(e);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				ExceptionUtil.wrapperException(e);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				ExceptionUtil.wrapperException(e);
			}
		}
		return ret;

	}

	/**
	 * 对象Set转List
	 * @param paraSet
	 * @return
	 */
	public static <T extends Object> List<T> setToList(Set<T> paraSet) {
		List<T> retList = new ArrayList<T>();
		retList.addAll(paraSet);
		return retList;
	}

   /**
    * 对象集合转列表
    * @param paraSet
    * @return
    */
	public static <T extends Object> List<T> collectionToList(
			Collection<T> paraSet) {
		List<T> retList = new ArrayList<T>();
		retList.addAll(paraSet);
		return retList;
	}

	/**
	 * 对象集合转Set
	 * @param para
	 * @return
	 */
	public static <T extends Object> Set<T> collectionToSet(Collection<T> para) {
		Set<T> retSet = new HashSet<T>();
		retSet.addAll(para);
		return retSet;
	}

	/**
	 * 求集合交集
	 * @param leftSet
	 * @param rightSet
	 * @return 交集Set
	 */
	public static <T extends Object> Set<T> minus(Set<T> leftSet,
			Set<T> rightSet) {
		Set<T> retSet = new HashSet<T>();
		for (T element : leftSet) {
			if (rightSet.contains(element)) {
				continue;
			}
			retSet.add(element);
		}

		return retSet;
	}

	/**
	 * 集合求并集
	 * @param left
	 * @param right
	 * @param clzz 对象类型
	 * @return 合并后对象数组
	 */
	public static <T> T[] combine(T[] left, T[] right, Class<T> clzz) {
		int length = left.length + right.length;
		@SuppressWarnings("unchecked")
		T[] retArray = (T[]) Array.newInstance(clzz, length);
		System.arraycopy(left, 0, retArray, 0, left.length);
		System.arraycopy(right, 0, retArray, left.length, right.length);
		return retArray;

	}
	
	/**
	 * 从字符数组匹配特定字符
	 * @param allStr 字符数组
	 * @param match 待匹配字符
	 * @return 是否存在匹配
	 */
	public static boolean isContains(String[] allStr, String match){
		if(allStr==null||allStr.length==0){
			return false;
		}
		List<String> strList= Arrays.asList(allStr);
		if(strList.contains(match)){
			return true;
		}
		return false;
	}
	
	
	public static String convertStr(Object[] objs, String seprator){
		StringBuilder str = new StringBuilder();
		if(ValueCheckUtil.isNullorZeroLength(objs)){
			return str.toString();
		}
		for(Object obj : objs){
			str.append(obj.toString());
			str.append(seprator);
		}
		str.deleteCharAt(str.length()-1);
		return str.toString();
		
	}
}
