package com.jiangcz.application.common.util.excelUtils.export;

import com.jiangcz.application.common.util.excelUtils.excel.ExcelColumn;
import com.jiangcz.application.common.util.excelUtils.excel.ExcelTag;
import com.jiangcz.application.common.util.excelUtils.excel.IExcelValueHandler;
import com.jiangcz.application.common.util.excelUtils.util.ValueCheckUtil;

import java.lang.reflect.Field;
import java.util.*;

public abstract class AbstractExcelExportUtil {

	/**
	 * 获取导出需要的column（不含importColumn=false的列）
	 * 
	 * @param clazz
	 * @param baseDocHandlers
	 * @return
	 */
	protected static <T> List<ExcelColumn> getExportColumns(Class<T> clazz,
															Map<String, IExcelValueHandler<?>> baseDocHandlers) {
		return getExportColumns(clazz, baseDocHandlers, false);
	}

	/**
	 * 获取导出需要的column
	 * 
	 * @param clazz
	 * @param baseDocHandlers
	 * @param exportColumn
	 *            exportColumn= false 时，ExcelTag的 importColumn = false 的属性不导出
	 * @return
	 */
	protected static <T> List<ExcelColumn> getExportColumns(Class<T> clazz,
															Map<String, IExcelValueHandler<?>> baseDocHandlers,
															boolean exportColumn) {

		List<ExcelColumn> ret = new ArrayList<ExcelColumn>();
//		Field[] fields = clazz.getDeclaredFields();
		List<Field> allFields = getAllFields(new LinkedList<Field>(), clazz);
		boolean isNullHandlers = ValueCheckUtil.isNullorZeroLength(baseDocHandlers);

		for (Field field : allFields) {
			if (!field.isAnnotationPresent(ExcelTag.class)) {
				continue;
			}

			ExcelTag tag = field.getAnnotation(ExcelTag.class);
			if (tag.index() == -1) {
				continue;
			}

			if (!exportColumn) {
				if (!tag.importColumn()) {
					continue;
				}
			}
			ExcelColumn column = new ExcelColumn();
			column.setIndex(tag.index());
			column.setTitle(tag.title());
			column.setType(tag.type());
			column.setRefIdField(tag.refIdField());
			column.setField(field.getName());
			column.setImportColumn(tag.importColumn());
			ret.add(column);
			if (isNullHandlers) {
				continue;
			}
			IExcelValueHandler<?> handler = baseDocHandlers
					.get(field.getName());
			if (handler != null) {
				column.registerHandler(handler);
			}

		}

		return ret;
	}

	
	/**
	 * 通过ExcelColumn.index得到ExcelColumn
	 * @param columns
	 * @param index
	 * @return
	 */
	protected static ExcelColumn getColumnByIndex(List<ExcelColumn> columns,
                                                  int index) {
		for (ExcelColumn temp : columns) {
			if (temp.getIndex().intValue() != index) {
				continue;
			}
			return temp;
		}
		return null;
	}

	/**
	 * 获取所有属性，包括继承自父类的属性
	 * @param fields
	 * @param type
	 * @return
	 */
	public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
		if (type.getSuperclass() != null) {
			fields = getAllFields(fields, type.getSuperclass());
		}
		fields.addAll(Arrays.asList(type.getDeclaredFields()));
		return fields;
	}

	public static void main(String[] args) {
		System.out.println(getAllFields(new LinkedList<Field>(), LinkedList.class));
	}
}