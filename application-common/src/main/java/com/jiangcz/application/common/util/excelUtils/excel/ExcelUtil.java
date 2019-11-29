package com.jiangcz.application.common.util.excelUtils.excel;



import com.jiangcz.application.common.util.excelUtils.exception.ExceptionUtil;
import com.jiangcz.application.common.util.excelUtils.export.AssertUtil;
import com.jiangcz.application.common.util.excelUtils.export.BaseTypeUtils;
import com.jiangcz.application.common.util.excelUtils.util.ValueCheckUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.*;

public class ExcelUtil {

	private static Logger log = Logger.getLogger(ExcelUtil.class);

	public static <T> List<T> importExcel2Obj(InputStream stream, Class<T> clazz) {

		List<ExcelColumn> columns = getImportColumns(clazz, null);
		return importExcel2Obj(stream, clazz, columns);
	}

	@SuppressWarnings("unchecked")
	private static <T> List<T> importExcel2Obj(InputStream stream,
			Class<T> clazz, List<ExcelColumn> columns) {

		T obj = null;
		List<T> ret = new ArrayList<T>();
		int rowIndex = 1;
		try {
			obj = (T) ConstructorUtils.invokeConstructor(clazz, null);
			String[][] excelContent = importExcel2Str(stream, 0, -1);

			for (; rowIndex < excelContent.length; rowIndex++) {
				String[] row = excelContent[rowIndex];
				T rowObj = (T) BeanUtils.cloneBean(obj);
				for (ExcelColumn column : columns) {
					//提示问题，不导入name没法提示 
					/*if (!StringUtils.isBlank(column.getRefIdField())) {
						continue;
					}*/
					String value = row[column.getIndex()];
					if (column.getType() == ExcelColumnType.Time) {
//						Date date = DateUtil.p.getDate(value);
//						value = PreorderTypeUtils.getSqlTimeStr(date);
					}

					String prop = column.getField();
					if (column.getFieldType().equals(Long.class) && StringUtils.isNotBlank(value)) {
						BeanUtils.setProperty(rowObj, prop, new BigDecimal(value).longValue());
					} else if (column.getFieldType().equals(Integer.class) && StringUtils.isNotBlank(value)) {
						BeanUtils.setProperty(rowObj, prop, new BigDecimal(value).intValue());
					} else {
						BeanUtils.setProperty(rowObj, prop, value);
					}
				}
				ret.add(rowObj);
			}

		} catch (Exception e) {
			int row = rowIndex+1;
			log.error("Excel导入失败"+"行号["+row+"]" , e);
			ExceptionUtil.wrapperException("excel转换实体失败,行号["+row+"]\n");
		}
		return ret;
	}

	public static <T> void exportObj2Excel(List<T> objs, String fileTitle,
			Map<String, IExcelValueHandler<?>> baseDocHandlers, OutputStream out) {

		if (ValueCheckUtil.isNullorZeroLength(objs)) {
			return;
		}
		List<ExcelColumn> columns = getExportColumns(objs.get(0).getClass(),
				baseDocHandlers);
		exportObj2Excel(objs, fileTitle, columns, out);

	}

	public static <T> void exportObj2Excel(List<T> objs, String fileTitle,
			OutputStream out) {

		if (ValueCheckUtil.isNullorZeroLength(objs)) {
			return;
		}
		List<ExcelColumn> columns = getExportColumns(objs.get(0).getClass(), null);
		exportObj2Excel(objs, fileTitle, columns, out);

	}
	/**
	 * 根据exportColumn 确定是否导出 注解标识的非导入字段
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
	 * 获取导入需要的Column
	 * @param clazz
	 * @param baseDocHandlers
	 * @return
	 */
	private static <T> List<ExcelColumn> getImportColumns(Class<T> clazz,
			Map<String, IExcelValueHandler<?>> baseDocHandlers) {

		List<ExcelColumn> ret = new ArrayList<ExcelColumn>();
		Field[] fields = clazz.getDeclaredFields();
		boolean isNullHandlers = ValueCheckUtil
				.isNullorZeroLength(baseDocHandlers);

		for (Field field : fields) {
			if (!field.isAnnotationPresent(ExcelTag.class)) {
				continue;
			}

			ExcelTag tag = field.getAnnotation(ExcelTag.class);
			if (tag.index() == -1) {
				continue;
			}
			if (!tag.importColumn()) {
				continue;
			}
			
			ExcelColumn column = new ExcelColumn();
			column.setIndex(tag.index());
			column.setTitle(tag.title());
			column.setType(tag.type());
			column.setRefIdField(tag.refIdField());
			column.setField(field.getName());
			column.setFieldType(field.getType());
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
	 * 获取导出需要的column
	 * importColumn = false 的属性不导出
	 * @param clazz
	 * @param baseDocHandlers
	 * @return
	 */
	private static <T> List<ExcelColumn> getExportColumns(Class<T> clazz,
			Map<String, IExcelValueHandler<?>> baseDocHandlers) {

		return getExportColumns( clazz,	 baseDocHandlers, false);
	}
	/**
	 * 
	 * @param clazz
	 * @param baseDocHandlers
	 * @param exportColumn  exportColumn= false 时，ExcelTag的 importColumn = false 的属性不导出
	 * @return
	 */
	private static <T> List<ExcelColumn> getExportColumns(Class<T> clazz,
			Map<String, IExcelValueHandler<?>> baseDocHandlers, boolean exportColumn) {

		List<ExcelColumn> ret = new ArrayList<ExcelColumn>();
		Field[] fields = clazz.getDeclaredFields();
		boolean isNullHandlers = ValueCheckUtil
				.isNullorZeroLength(baseDocHandlers);

		for (Field field : fields) {
			if (!field.isAnnotationPresent(ExcelTag.class)) {
				continue;
			}

			ExcelTag tag = field.getAnnotation(ExcelTag.class);
			if (tag.index() == -1) {
				continue;
			}
			
			if(!exportColumn){
				if(!tag.importColumn()){
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
	private static <T> void exportObj2Excel(List<T> objs, String fileTitle,
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
					} else{// 当没有handler 是，默认从对象的属性中取值
						String value = props.get(column.getField());
						content[i][column.getIndex()] = value;
					}
				}

			}

					
			exportStr2Excel(fileTitle, columns, title, content, out);

		} catch (Exception e) {
			log.error("Excel导出失败" + e.getMessage()+" cause:"+e.getCause());
			ExceptionUtil.wrapperException(e);
		}

	}

	/**
	 * 
	 * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
	 * 
	 * @param file
	 *            读取数据的源Excel
	 * 
	 * @param ignoreRows
	 *            读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
	 * 
	 * @return 读出的Excel中数据的内容
	 * 
	 * @throws FileNotFoundException
	 * 
	 * @throws IOException
	 * @throws InvalidFormatException
	 */

	public static String[][] importExcel2Str(File file, int ignoreRows)
			throws FileNotFoundException, IOException, InvalidFormatException {

		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				file));

		return importExcel2Str(in, ignoreRows, -1);
	}

	/**
	 * 
	 * 去掉字符串右边的空格
	 * 
	 * @param str
	 *            要处理的字符串
	 * 
	 * @return 处理后的字符串
	 */

	public static String rightTrim(String str) {

		if (str == null) {

			return "";

		}

		int length = str.length();

		for (int i = length - 1; i >= 0; i--) {

			if (str.charAt(i) != 0x20) {

				break;

			}

			length--;

		}

		return str.substring(0, length);

	}

	/**
	 * 从Excel的stream输出[开始行，结束行]的文本
	 * 
	 * @param stream
	 * @param startIndex
	 *            开始行数 .包含
	 * @param endIndex
	 *            结束行数 包含
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 */

	public static String[][] importExcel2Str(InputStream stream,
			int startIndex, int endIndex) throws IOException, InvalidFormatException
			 {

		List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;

		// 打开Workbook
		Workbook wb = null;

		wb = WorkbookFactory.create(stream);

		Cell cell = null;


		Sheet st = wb.getSheetAt(0);

		int lastRowNum = endIndex == -1 ? st.getLastRowNum() : endIndex;

		for (int rowIndex = startIndex; rowIndex <= lastRowNum; rowIndex++) {

			Row row = st.getRow(rowIndex);

			if (row == null) {

				continue;

			}

			int tempRowSize = row.getLastCellNum();

			if (tempRowSize > rowSize) {

				rowSize = tempRowSize;

			}

			String[] values = new String[rowSize];

			Arrays.fill(values, "");

			boolean hasValue = false;
			
			int i = 0;
			
			for (short columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {

				String value = "";
                
				cell = row.getCell(columnIndex);

				if (cell != null) {

					// 注意：一定要设成这个，否则可能会出现乱码
					// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					// System.out.println(cell.getCellType());
					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_STRING:

						value = cell.getStringCellValue();

						break;

					case Cell.CELL_TYPE_NUMERIC:

						if (DateUtil.isCellDateFormatted(cell)) {

							Date date = cell.getDateCellValue();

							if (date != null) {

								value = BaseTypeUtils.getSysTimeStr(date);

							} else {

								value = "";

							}

						} else {

							// value = new
							// DecimalFormat("#").format(cell.getNumericCellValue());
							value = new BigDecimal(
									cell.getNumericCellValue())
									.toPlainString();
						}

						break;

					case Cell.CELL_TYPE_FORMULA:

						// 导入时如果为公式生成的数据则无值

						if (!cell.getStringCellValue().equals("")) {

							value = cell.getStringCellValue();

						} else {

							value = cell.getNumericCellValue() + "";

						}

						break;

					case Cell.CELL_TYPE_BLANK:

						break;

					case Cell.CELL_TYPE_ERROR:

						value = "";

						break;

					case Cell.CELL_TYPE_BOOLEAN:

						value = (cell.getBooleanCellValue() == true ? "Y"
								: "N");

						break;

					default:

						value = "";

					}

				}

				if (value.trim().equals("")) {

					i++;

				}
				
				
				values[columnIndex] = rightTrim(value);

				hasValue = true;
				//所有列都为空
				if(columnIndex == row.getLastCellNum() - 1 && i == row.getLastCellNum() ){
					
					hasValue = false;
					
					break;
					
				}

			}

			if (hasValue) {

				result.add(values);

			}

		}


		stream.close();

		String[][] returnArray = new String[result.size()][rowSize];

		for (int i = 0; i < returnArray.length; i++) {

			returnArray[i] = (String[]) result.get(i);

		}

		return returnArray;
	}

	public static boolean saveData(String path, String fileName,
			String[] heads, String[][] data) {

		try {

			AssertUtil.isTrue(data != null, "保存数据为空");
			AssertUtil.isTrue(heads.length == data[0].length, "标题与内容长度不一致");

			File file = new File(path);
			if (!file.exists())
				file.mkdirs();

			File errorFile = new File(path + File.separator + fileName);

			errorFile.createNewFile();

			OutputStream os = new FileOutputStream(path + File.separator
					+ fileName);

			Workbook wb = new SXSSFWorkbook();

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
			log.error("保存excel失败," + e.getMessage()+"cause["+e.getCause()+"]");
			ExceptionUtil.wrapperException("保存excel失败," + e.getMessage()+"cause["+e.getCause()+"]");
		} catch (IOException e) {
			log.error("保存excel失败," + e.getMessage()+"cause["+e.getCause()+"]");
			ExceptionUtil.wrapperException("保存excel失败," + e.getMessage()+"cause["+e.getCause()+"]");
		}

		return true;
	}

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
			ExceptionUtil.wrapperException(e.getMessage());
		} catch (InvocationTargetException e) {
			log.error("Excel导出失败" + e.getMessage()+"cause["+e.getCause()+"]");
			ExceptionUtil.wrapperException(e.getMessage());
		} catch (NoSuchMethodException e) {
			log.error("Excel导出失败" + e.getMessage()+"cause["+e.getCause()+"]");
			ExceptionUtil.wrapperException(e.getMessage());
		}

	}

	public static void exportStr2Excel(String title, List<ExcelColumn> columns,
			String[] headers, String[][] content, OutputStream out) {
		
		Date begin = logBegin("纯文本导出");

		// 声明一个工作薄
		Workbook workbook = new SXSSFWorkbook();
		// 生成一个表格
		Sheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);

		CellStyle timeStyle = workbook.createCellStyle();
		DataFormat format = workbook.createDataFormat();
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
		logEnd("纯文本导出", begin);
	}

	private static ExcelColumn getColumnByIndex(List<ExcelColumn> columns,
			int index) {
		for (ExcelColumn temp : columns) {
			if (temp.getIndex().intValue() != index) {
				continue;
			}
			return temp;
		}
		return null;
	}
	
	private static  Date logBegin(String msg) {

		
		Date beginTime = BaseTypeUtils.getSystemDate();
		log.info("###DANGDANG LOG : " + msg + " begin at [" + beginTime+"]  #####");
		return beginTime;
	}
	
	private static  void logEnd(String msg,Date beginTime){
		Date endTime = BaseTypeUtils.getSystemDate();
		Long cost = endTime.getTime() - beginTime.getTime();
		log.info("####DANGDANG LOG :  "+msg +" end at ["+endTime+"] ; cost ["+cost+"] ms  #####");
	}

}
