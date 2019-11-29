package com.jiangcz.application.common.util.excelUtils.export;

import com.jiangcz.application.common.util.excelUtils.excel.ExcelColumn;
import com.jiangcz.application.common.util.excelUtils.excel.ExcelColumnType;
import com.jiangcz.application.common.util.excelUtils.excel.IExcelValueHandler;
import com.jiangcz.application.common.util.excelUtils.util.ValueCheckUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SimpleExcelExportUtil extends AbstractExcelExportUtil {
	
	static Logger log = LoggerFactory.getLogger(SimpleExcelExportUtil.class);

	public static <T> void exportObj2Excel(List<T> objs, String fileTitle,
										   Map<String, IExcelValueHandler<?>> baseDocHandlers, OutputStream out) {

		if (ValueCheckUtil.isNullorZeroLength(objs)) {
			return;
		}
		List<ExcelColumn> columns = getExportColumns(objs.get(0).getClass(),
				baseDocHandlers);
		exportObj2Excel(objs, fileTitle, columns, out);

	}

	/**
	 * 导出，不导出注解标识的非导出字段
	 * @param objs
	 * @param fileTitle
	 * @param out
	 */
	public static <T> void exportObj2Excel(List<T> objs, String fileTitle,
			OutputStream out) {

		if (ValueCheckUtil.isNullorZeroLength(objs)) {
			return;
		}
		List<ExcelColumn> columns = getExportColumns(objs.get(0).getClass(), null);
		exportObj2Excel(objs, fileTitle, columns, out);

	}
	/**
	 * 导出exel
	 * @param objs
	 * @param fileTitle
	 * @param out
	 * @param exportColumn
	 */
	public static <T> void exportObj2Excel(List<T> objs, String fileTitle,
			OutputStream out,boolean exportColumn) {

		if (ValueCheckUtil.isNullorZeroLength(objs)) {
			return;
		}
		List<ExcelColumn> columns = getExportColumns(objs.get(0).getClass(), null,exportColumn);
		exportObj2Excel(objs, fileTitle, columns, out);

	}
	
	/**
	 * 导出excel
	 * 
	 * @param objs
	 * @param fileTitle
	 * @param columns
	 * @param out
	 */
	protected static <T> void exportObj2Excel(List<T> objs, String fileTitle,
                                              List<ExcelColumn> columns, OutputStream out) {
		try {

			String[] title = new String[columns.size()];
			for (ExcelColumn column : columns) {
				title[column.getIndex()] = column.getTitle();

			}
			String[][] content = new String[objs.size()][columns.size()];
			for (int i = 0; i < objs.size(); i++) {
				T temp = objs.get(i);
				@SuppressWarnings("unchecked")
				Map<String, String> props = BeanUtils.describe(temp);
				for (ExcelColumn column : columns) {
					if (StringUtils.isBlank(column.getRefIdField())) {
						String value = props.get(column.getField());
						content[i][column.getIndex()] = value;
					} else if (column.getHandler() != null) {
						IExcelValueHandler<T> handler = column.getHandler();
						String value = handler.getValue(temp, column);
						content[i][column.getIndex()] = value;
					} else {// 当没有handler 是，默认从对象的属性中取值
						String value = props.get(column.getField());
						content[i][column.getIndex()] = value;
					}
				}

			}

			exportStr2Excel(fileTitle, columns, title, content, out);

		} catch (Exception e) {
			log.error("Excel导出失败" + e.getMessage() + " cause:" + e.getCause());
		}

	}

	public static boolean exportStr2Excel(String path, String fileName,
			String[] heads, String[][] data) {

		try {

			com.jiangcz.application.common.util.excelUtils.export.AssertUtil.isTrue(data != null, "保存数据为空");
			com.jiangcz.application.common.util.excelUtils.export.AssertUtil.isTrue(heads.length == data[0].length, "标题与内容长度不一致");

			File file = new File(path);
			if (!file.exists())
				file.mkdirs();

			File errorFile = new File(path + File.separator + fileName);

			errorFile.createNewFile();

			OutputStream os = new FileOutputStream(path + File.separator
					+ fileName);

			Workbook wb = new XSSFWorkbook();

			Sheet sheet = wb.createSheet();
			// 创建表头
			Row row = sheet.createRow(0);
			for (int i = 0; i < heads.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(heads[i]);
			}

			Cell tempCell;
			for (int i = 0; i < data.length; i++) {

				String[] rowData = data[i];

				row = sheet.createRow(i + 1);

				for (int j = 0; j < rowData.length; j++) {
					tempCell = row.createCell(j);
					tempCell.setCellValue(rowData[j]);
				}
			}

			wb.write(os);

			os.flush();
			os.close();

		} catch (FileNotFoundException e) {
			log.error("保存excel失败," + e.getMessage() + "cause[" + e.getCause()
					+ "]");
		} catch (IOException e) {
			log.error("保存excel失败," + e.getMessage() + "cause[" + e.getCause()
					+ "]");
		}

		return true;
	}

	/**
	 * 纯文本导出excel
	 * 
	 * @param title
	 * @param columns
	 * @param headers
	 * @param content
	 * @param out
	 */
	protected static void exportStr2Excel(String title,
                                          List<ExcelColumn> columns, String[] headers, String[][] content,
                                          OutputStream out) {

		// 声明一个工作薄
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 生成一个表格
		Sheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);

		XSSFCellStyle timeStyle = workbook.createCellStyle();
		XSSFDataFormat format = workbook.createDataFormat();
		short formatIndex = format.getFormat("hh:mm");
		// 建立新的cell样式
		timeStyle.setDataFormat(formatIndex);

		// 产生表格标题行
		Row row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			Cell cell = row.createCell(i);
			XSSFRichTextString text = new XSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		// 遍历集合数据，产生数据行
		for (int i = 0; i < content.length; i++) {

			row = sheet.createRow(i + 1);

			String[] rowArray = content[i];

			for (int j = 0; j < rowArray.length; j++) {

				String value = rowArray[j];

				Cell cell = row.createCell(j);
				ExcelColumn column = getColumnByIndex(columns, j);
				if (column.getType() == ExcelColumnType.Time) {

					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellStyle(timeStyle);
					if (!StringUtils.isBlank(value)) {
						Time time = Time.valueOf(value);
						cell.setCellValue(time);
					}
				} else {
					XSSFRichTextString richString = new XSSFRichTextString(
							value);
					cell.setCellValue(richString);
				}

			}
		}

		try {
			workbook.write(out);
		} catch (IOException e) {
			log.error("导出excel失败," + e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param objs
	 * @param fileTitle
	 * @param titleAndFields
	 * @param out
	 */
	public static <T> void exportObj2Excel(List<T> objs, String fileTitle,
			LinkedHashMap<String, String> titleAndFields, OutputStream out) {

		try {
			String[][] content = new String[objs.size() + 1][titleAndFields
					.size()];
			for (int i = 0; i < objs.size(); i++) {
				T temp = objs.get(i);
				@SuppressWarnings("unchecked")
				Map<String, String> props = BeanUtils.describe(temp);

				int j = 0;
				for (Map.Entry<String, String> entry : titleAndFields
						.entrySet()) {
					if (i == 0) {
						content[i][j] = entry.getKey();
					} else {
						String value = props.get(entry.getValue());
						content[i][j] = value;
					}
					j++;
				}

			}

			exportStr2Excel(fileTitle, null,
					titleAndFields.keySet().toArray(new String[0]), content,
					out);
		} catch (IllegalAccessException e) {
			log.error("Excel导出失败" + e.getMessage()+"cause["+e.getCause()+"]");
		} catch (InvocationTargetException e) {
			log.error("Excel导出失败" + e.getMessage()+"cause["+e.getCause()+"]");
		} catch (NoSuchMethodException e) {
			log.error("Excel导出失败" + e.getMessage()+"cause["+e.getCause()+"]");
		}

	}
	
}
