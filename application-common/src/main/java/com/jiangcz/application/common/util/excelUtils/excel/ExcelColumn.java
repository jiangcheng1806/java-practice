package com.jiangcz.application.common.util.excelUtils.excel;

public class ExcelColumn {
		
	private Integer index;
	
	private String title;
	
	private String field;

	private Class fieldType;

	private ExcelColumnType type;
	
	private String refIdField;

	private boolean importColumn;
	
	private IExcelValueHandler<?> handler;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public ExcelColumnType getType() {
		return type;
	}

	public void setType(ExcelColumnType type) {
		this.type = type;
	}
	

	@SuppressWarnings("unchecked")
	public<T> IExcelValueHandler<T> getHandler() {
		return (IExcelValueHandler<T>) handler;
	}

	public <T> void registerHandler(IExcelValueHandler<T> handler) {
		this.handler = handler;
	}

	public String getRefIdField() {
		return refIdField;
	}

	public void setRefIdField(String refIdField) {
		this.refIdField = refIdField;
	}

	public boolean isImportColumn() {
		return importColumn;
	}

	public void setImportColumn(boolean importColumn) {
		this.importColumn = importColumn;
	}

	public Class getFieldType() {
		return fieldType;
	}

	public void setFieldType(Class fieldType) {
		this.fieldType = fieldType;
	}
}
