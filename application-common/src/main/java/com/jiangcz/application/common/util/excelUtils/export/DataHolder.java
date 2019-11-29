package com.jiangcz.application.common.util.excelUtils.export;


import com.jiangcz.application.common.util.excelUtils.excel.ExcelColumn;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;


class DataHolder {
	private Sheet _sheet = null;
	private List<ExcelColumn> _columns = null;
	private int _rowIndex = 0;
	
	private static final ThreadLocal<DataHolder> _localContext =
			new ThreadLocal<DataHolder>() {
		
		public DataHolder initialValue() {
			return new DataHolder();
		}
	};

	private DataHolder() {
	}

	public static void clear() {
		_localContext.get()._sheet = null;
		_localContext.get()._columns = null;
		_localContext.get()._rowIndex = 0;
		_localContext.remove();
	}
	
	public static int getRowIndex(){
		return _localContext.get()._rowIndex;
	}
	
	public static void setRowIndex(int rowIndex){
		_localContext.get()._rowIndex = rowIndex;
	}
	

	public static Sheet getSheet() {
		return _localContext.get()._sheet;
	}

	public static void setSheet(Sheet sheet) {
		_localContext.get()._sheet = sheet;
	}

	public static List<ExcelColumn> getColumns() {
		return _localContext.get()._columns;
	}

	public static void setColumns(List<ExcelColumn> columns) {
		_localContext.get()._columns = columns;
	}

}
