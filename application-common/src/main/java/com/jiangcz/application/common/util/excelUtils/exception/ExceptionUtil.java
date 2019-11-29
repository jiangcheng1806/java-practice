package com.jiangcz.application.common.util.excelUtils.exception;

/**
 * 异常处理类
 * @author chennina
 *
 */
public class ExceptionUtil {

	/**
	 * 转换声明式异常
	 * @param ex
	 * @return RuntimeException
	 */
	public static RuntimeException wrapperException(Exception ex) {
		throw new RuntimeException(ex);
	}

	
	/**
	 * 抛出异常
	 * @param cause 异常信息
	 * @return RuntimeException
	 */
	public static RuntimeException wrapperException(String cause){
		throw new RuntimeException(cause);
	}
	
	
	/**
	 * 异常转换
	 * @param error 错误
	 * @return RuntimeException
	 */
	public static RuntimeException wrapperException(Error error){
		throw new RuntimeException(error.getCause());
	}
}
