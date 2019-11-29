package com.jiangcz.application.common.util.excelUtils.excel;


import com.jiangcz.application.common.util.BaseDateUtils;
import com.jiangcz.application.common.util.excelUtils.exception.ExceptionUtil;
import com.jiangcz.application.common.util.excelUtils.util.ValueCheckUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CSVUtil {

	private static Logger log = Logger.getLogger(CSVUtil.class);
	
	public static <T> void exportObj2CSV(List<T> objs, String fileTitle,
                                         Map<String, IExcelValueHandler<?>> baseDocHandlers, OutputStream out, String fileName) {

		if (ValueCheckUtil.isNullorZeroLength(objs)) {
			return;
		}
		List<ExcelColumn> columns = getExportColumns(objs.get(0).getClass(),
				baseDocHandlers);
		exportObj2CSV(objs, fileTitle, columns, out, fileName);

	}
	private static <T> void exportObj2CSV(List<T> objs, String fileTitle,
                                          List<ExcelColumn> columns, OutputStream out, String fileName) {
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

			exportStr2CSV(title, content, out, fileName);

		} catch (Exception e) {
			log.error("CSV导出失败" + e.getMessage()+" cause:"+e.getCause());
			ExceptionUtil.wrapperException(e);
		}

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

	public static void exportStr2CSV(String[] headers, String[][] content, OutputStream out, String fileName) throws Exception {
		
		Date begin = logBegin("纯文本导出");
		ZipOutputStream zos = new ZipOutputStream(out);
		zos.putNextEntry(new ZipEntry(fileName));
		try {
			StringBuffer headerCSV = new StringBuffer();
			for (short i = 0; i < headers.length; i++) {
				if(i == 0){
					headerCSV.append(headers[i]);
				}
				else{
					headerCSV.append(",").append(headers[i]);
				}
			}
			headerCSV.append("\n");
			byte[] headerBytes = headerCSV.toString().getBytes("gbk");
			zos.write(headerBytes);
			// 遍历集合数据，产生数据行
			for (int i = 0; i < content.length; i++) {
				StringBuffer contentCSV = new StringBuffer();
				String[] rowArray = content[i];
	
				for (int j = 0; j < rowArray.length; j++) {
					String value = rowArray[j];
					value = value == null ? "" : value;
	                //csv以,分割列，加“”号，区分内容中的,
					StringBuffer sb = new StringBuffer();
					if (value.startsWith("=")) {
						sb.append(value); // 等号开头的可能是公式，或者要转文本的日期
					} else if (value.startsWith("\"") && value.endsWith("\"")) {
						sb.append(value);
					} else { // 自动加双引号
						sb.append("\"").append(value).append("\"");
					}

	            	if(j == 0){
						contentCSV.append(sb.toString());
					}
					else{
						contentCSV.append(",").append(sb.toString());
					}
				}
				contentCSV.append("\n");
				byte[] rowBytes = contentCSV.toString().getBytes("gbk");
				zos.write(rowBytes);
			}

		} catch (IOException e) {
			log.error("导出csv失败," + e.getMessage());
		}finally{
			zos.close();
		}
		logEnd("纯文本导出", begin);
	}
	
	
	private static  Date logBegin(String msg) {

		
		Date beginTime = BaseDateUtils.getSystemDate();
		log.info("###DANGDANG LOG : " + msg + " begin at [" + beginTime+"]  #####");
		return beginTime;
	}
	
	private static  void logEnd(String msg,Date beginTime){
		Date endTime = BaseDateUtils.getSystemDate();
		Long cost = endTime.getTime() - beginTime.getTime();
		log.info("####DANGDANG LOG :  "+msg +" end at ["+endTime+"] ; cost ["+cost+"] ms  #####");
	}

}
