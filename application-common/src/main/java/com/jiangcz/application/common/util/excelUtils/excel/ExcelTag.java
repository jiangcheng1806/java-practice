package com.jiangcz.application.common.util.excelUtils.excel;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelTag {
	
	/**
	 * excel 列顺序
	 * @return
	 */
	public int index() default -1;
	/**
	 * excel 导入字段
	 * @return
	 */
	public boolean importColumn() default true;
	/**
	 * excel标题
	 * @return
	 */
	public String title() default "";

	/**
	 * 是否持久化
	 * 档案名称：仓库名称 不是持久化属性
	 * @return
	 */
	public boolean persistent() default true;
	
	/**
	 * Excel列类型
	 * @return
	 */
	public ExcelColumnType type() default ExcelColumnType.Common;
	
	public String refIdField() default "";
}
