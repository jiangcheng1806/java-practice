package com.jiangcz.application.common.util.excelUtils.export;


import com.jiangcz.application.common.util.excelUtils.excel.ExcelColumn;
import com.jiangcz.application.common.util.excelUtils.excel.ExcelColumnType;
import com.jiangcz.application.common.util.excelUtils.excel.IExcelValueHandler;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.sql.Time;
import java.util.List;
import java.util.Map;

public class BigExcelExportUtil extends AbstractExcelExportUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(BigExcelExportUtil.class);
	
	private static int rowAccess = 500;   //内存中缓存的行数

	public static void prepare() throws FileNotFoundException, IOException {
		//clear dataholder
		DataHolder.clear();
		
		Workbook wb = new SXSSFWorkbook(rowAccess);
		Sheet sheet = wb.createSheet();
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 20);
		
		//new dataholder
//		DataHolder.setWorkbook(wb);
		DataHolder.setSheet(sheet);
	}

	/**
	 * 根据模板生产Excel文件
	 * @param templatePath  模板路径
	 * @throws FileNotFoundException
	 * @throws IOException
     */
	public static void prepareByTemplate(String templatePath) throws FileNotFoundException, IOException {
		//clear dataholder
		DataHolder.clear();

		InputStream in = new FileInputStream(templatePath);
		XSSFWorkbook xssfWorkbook=new XSSFWorkbook(in);

		Workbook wb =new SXSSFWorkbook(xssfWorkbook,rowAccess);

		Sheet sheet = wb.createSheet();
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);

		DataHolder.setSheet(sheet);
	}
	
	public static void writeHeader(String[] header) {
		try{
			Sheet sheet = DataHolder.getSheet();
			int startRowIndex = DataHolder.getRowIndex();
			// 创建表头
			Row row = sheet.createRow(startRowIndex);
			for (int i = 0; i < header.length; i++) {
				Cell cell = row.createCell(i);
				XSSFRichTextString text = new XSSFRichTextString(header[i]);
				cell.setCellValue(text);
			}
			startRowIndex ++;
			DataHolder.setRowIndex(startRowIndex);
		}
		catch(Exception e){
			logger.error("write excel header error", e);
		}
	}
	
	public static <T> void writeHeader(Class<T> clazz){
		writeHeader(clazz, null, false);
	}
	
	public static <T> void writeHeader(Class<T> clazz, boolean exportAllColumn){
		writeHeader(clazz, null, exportAllColumn);
	}
	
	public static <T> void writeHeader(Class<T> clazz, Map<String, IExcelValueHandler<?>> baseDocHandlers, boolean exportAllColumn) {
		try {
			List<ExcelColumn> columns = getExportColumns(clazz, baseDocHandlers,exportAllColumn);
			DataHolder.setColumns(columns);
			String[] header = new String[columns.size()];
			for (ExcelColumn column : columns) {
				header[column.getIndex()] = column.getTitle();
			}
			writeHeader(header);

		} catch (Exception e) {
			logger.error("Excel导出失败" + e.getMessage() + " cause:" + e.getCause());
		}

	}
	
	
	public static void writeContent(String[][] data){
		try{
			Sheet sheet = DataHolder.getSheet();
			int startRowIndex = DataHolder.getRowIndex();
			Row row ;
			Cell tempCell;
			for (int i = 0; i < data.length; i++) {

				String[] rowData = data[i];

				row = sheet.createRow(startRowIndex);

				for (int j = 0; j < rowData.length; j++) {
					tempCell = row.createCell(j);
					XSSFRichTextString text = new XSSFRichTextString(rowData[j]);
					tempCell.setCellValue(text);
				}
				startRowIndex++;
			}
			DataHolder.setRowIndex(startRowIndex);
		}
		catch(Exception e){
			logger.error("write excel header error", e);
		}
	}
	
	public static <T> void writeContent(List<T> objs){
		try {
			List<ExcelColumn> columns = DataHolder.getColumns();
			String[][] data = new String[objs.size()][columns.size()];
			for (int i = 0; i < objs.size(); i++) {
				T temp = objs.get(i);
				@SuppressWarnings("unchecked")
				Map<String, String> props = BeanUtils.describe(temp);
				for (ExcelColumn column : columns) {
					if (StringUtils.isBlank(column.getRefIdField())) {
						String value = props.get(column.getField());
						data[i][column.getIndex()] = value;
					} else if (column.getHandler() != null) {
						IExcelValueHandler<T> handler = column.getHandler();
						String value = handler.getValue(temp, column);
						data[i][column.getIndex()] = value;
					} else {// 当没有handler 是，默认从对象的属性中取值
						String value = props.get(column.getField());
						data[i][column.getIndex()] = value;
					}
				}
			}

			Sheet sheet = DataHolder.getSheet();
			Workbook workbook = sheet.getWorkbook();
			int startRowIndex = DataHolder.getRowIndex();

			CellStyle timeStyle = workbook.createCellStyle();
			//垂直居中
			timeStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 水平居中
			timeStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			DataFormat format = workbook.createDataFormat();
			short formatIndex = format.getFormat("hh:mm");
			// 建立新的cell样式
			timeStyle.setDataFormat(formatIndex);

			Row row ;
			Cell cell;
			for (int i = 0; i < data.length; i++) {

				String[] rowData = data[i];

				row = sheet.createRow(startRowIndex);

				for (int j = 0; j < rowData.length; j++) {
					String value = rowData[j];
					cell = row.createCell(j);
					ExcelColumn column = getColumnByIndex(columns, j);
					if (column.getType() == ExcelColumnType.Time) {

						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellStyle(timeStyle);
						if (!StringUtils.isBlank(value)) {
							Time time = Time.valueOf(value);
							cell.setCellValue(time);
						}
					} else {
						XSSFRichTextString richString = new XSSFRichTextString(value);
						cell.setCellValue(richString);
					}
				}
				startRowIndex++;
			}
			DataHolder.setRowIndex(startRowIndex);

		} catch (Exception e) {
			logger.error("Excel导出失败",e);
		}
	}
	
	public static void flush(File file){
		OutputStream os = null;
		try{
			Sheet sheet = DataHolder.getSheet();
			Workbook wb = sheet.getWorkbook();
			os = new FileOutputStream(file);
			wb.write(os);
			os.flush();
		}
		catch(Exception e){
			logger.error("flush excel header error", e);
		}finally{
			IOUtils.closeQuietly(os);
		}
	}
	
	public static void flushAndClose(ServletOutputStream out){
		String filePath = "";
		try{
			Sheet sheet = DataHolder.getSheet();
			Workbook wb = sheet.getWorkbook();
			wb.write(out);
			out.flush();
//			filePath = FastDfsUtils.uploadExcel(out,"xlsx");
//			filePath = "";
		}
		catch(Exception e){
			logger.error("flush excel header error", e);
		}finally{
			IOUtils.closeQuietly(out);
			CSVDataHolder.clear();
		}
//		return filePath;
	}

}
