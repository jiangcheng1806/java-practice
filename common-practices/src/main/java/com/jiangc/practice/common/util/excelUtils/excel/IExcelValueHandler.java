package com.jiangc.practice.common.util.excelUtils.excel;

public interface IExcelValueHandler<T> {
	
	public String getValue(T obj, ExcelColumn column);

}
