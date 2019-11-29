package com.jiangcz.application.common.util.excelUtils.export;


import com.jiangcz.application.common.util.excelUtils.excel.ExcelColumn;

import java.io.OutputStream;
import java.util.List;


class CSVDataHolder {
	private OutputStream _out =null;
	private List<ExcelColumn> _columns = null;
	private Integer _rowNo=0;

	private static final ThreadLocal<CSVDataHolder> _localContext =
			new ThreadLocal<CSVDataHolder>() {

		public CSVDataHolder initialValue() {
			return new CSVDataHolder();
		}
	};

	private CSVDataHolder() {
	}

	public static void clear() {
		_localContext.get()._rowNo = 0;
		_localContext.get()._out = null;
		_localContext.get()._columns = null;
		_localContext.remove();
	}


	public static Integer getRowNo() {
		return _localContext.get()._rowNo;
	}

	public static void setRowNo(Integer  rowNo) {
		_localContext.get()._rowNo = rowNo;
	}
	public static OutputStream getOut() {
		return _localContext.get()._out;
	}

	public static void setOut(OutputStream  zos) {
		_localContext.get()._out = zos;
	}
	public static List<ExcelColumn> getColumns() {
		return _localContext.get()._columns;
	}

	public static void setColumns(List<ExcelColumn> columns) {
		_localContext.get()._columns = columns;
	}

}
