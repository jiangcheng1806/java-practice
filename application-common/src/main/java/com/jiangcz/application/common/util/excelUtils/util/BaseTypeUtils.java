package com.jiangcz.application.common.util.excelUtils.export;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * JAVA 基本类型处理工具类
 * @author chennina
 *
 */
public class BaseTypeUtils {

	/**
	 * 获取时间"yyyy-MM-dd HH:mm:ss"字符表示
	 * @param date 日期
	 * @return 字符
	 */
	public static String getSysTimeStr(Date date){
		if(date == null){
		  return getSysTimeStr();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	
	/**
	 * 获取当前时间"yyyy-MM-dd HH:mm:ss"字符表示
	 * @return 表示字符
	 */
	public static String getSysTimeStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date()); 
	}
	
	/**
	 * 获取系统日期
	 * @return
	 */
	public static Date getSystemDate(){
		return new Date();
	}
	
	/**
	 * 获取系统日期"yyyy-MM-dd"字符
	 * @return
	 */
	public static String getSysDateStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date()); 
	}
	
	/**
	 * 获取日期"yyyy-MM-dd"字符
	 * @param paraDate 日期
	 * @return 字符
	 */
	public static String getSysDateStr(Date paraDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(paraDate); 
	}
	
	/**
	 * 获取"yyyy-MM-dd"类型日期
	 * @param dateStr
	 * @return
	 */
	public static Date getDayDate(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return null;
	}
	/**
	 * 获取n天前的"yyyy-MM-dd HH:mm:ss"字符表示
	 * @param days 提前天数
	 * @return 返回字符串
	 */
	public static String getBeforeDaysTime(int days){
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -days);
		Date date = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date); 
		
	}
	
	/**
	 * 转换"yyyy-MM-dd HH:mm:ss"字符为日期类型
	 * @param dateStr 字符类型
	 * @return 日期
	 */
	public static Date getDate(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return null;
	}
	



}
