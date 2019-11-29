package com.jiangcz.application.common.util.excelUtils.export;


import com.jiangcz.application.common.util.excelUtils.excel.ExcelColumn;
import com.jiangcz.application.common.util.excelUtils.excel.IExcelValueHandler;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BigCSVExportUtil extends AbstractExcelExportUtil{
	
	private final static Logger logger = LoggerFactory.getLogger(BigCSVExportUtil.class);

	public static <T> void writeHeaderByDataHolder(Class<T> clazz, Map<String, IExcelValueHandler<?>> baseDocHandlers, OutputStream out,
												   String fileName, boolean exportAllColumn) {
		CSVDataHolder.clear();
		List<ExcelColumn> columns = getExportColumns(clazz, baseDocHandlers, exportAllColumn);
		CSVDataHolder.setColumns(columns);

		ZipOutputStream zos = null;
		if (out instanceof ZipOutputStream) {
			zos = (ZipOutputStream) out;
		} else {
			zos = new ZipOutputStream(out);
		}

		try {
			zos.putNextEntry(new ZipEntry(fileName));

			StringBuilder headerCSV = new StringBuilder();
			for (ExcelColumn column : columns) {
				if (headerCSV.length()==0) {
					headerCSV.append(column.getTitle());
				} else {
					headerCSV.append(",").append(column.getTitle());
				}
			}
			headerCSV.append("\n");
			byte[] headerBytes = headerCSV.toString().getBytes("gbk");
			zos.write(headerBytes);
			CSVDataHolder.setOut(zos);
		} catch (Exception e) {
			logger.error("csv导出失败" + e.getMessage() + " cause:" + e.getCause());
		}
	}


	public static <T> void writeContent(List<T> objs) {
		OutputStream bos = CSVDataHolder.getOut();
		Integer rowNo = CSVDataHolder.getRowNo();
		try {
			List<ExcelColumn> columns = CSVDataHolder.getColumns();
			for (int i = 0; i < objs.size(); i++) {
				T temp = objs.get(i);
				StringBuffer csvLine = new StringBuffer();
				rowNo++;
				String[] rowData = new String[columns.size()];
				Map<String, String> props = BeanUtils.describe(temp);
				for (ExcelColumn column : columns) {
					if (column.getIndex() == 0) {  //序号列
						rowData[column.getIndex()] = rowNo.toString();
					} else if (StringUtils.isBlank(column.getRefIdField())) {
						String value = props.get(column.getField());
						rowData[column.getIndex()] = value;
					} else if (column.getHandler() != null) {
						IExcelValueHandler<T> handler = column.getHandler();
						String value = handler.getValue(temp, column);
						rowData[column.getIndex()] = value;
					} else {// 当没有handler 是，默认从对象的属性中取值
						String value = props.get(column.getField());
						rowData[column.getIndex()] = value;
					}

					if (StringUtils.isBlank(rowData[column.getIndex()])) {
						rowData[column.getIndex()] = "";
					}
					//csv每一个值添加 ""
					rowData[column.getIndex()] = "\"" + rowData[column.getIndex()] + "\"";
				}
				csvLine.append(Arrays.toString(rowData).replaceAll(", ",",").replaceAll("[\\[\\]]",""));
				csvLine.append("\n");
				byte[] rowBytes = csvLine.toString().getBytes("gbk");
				bos.write(rowBytes);
			}
			CSVDataHolder.setRowNo(rowNo);

		} catch (Exception e) {
			logger.error("Excel导出失败" + e.getMessage() + " cause:" + e.getCause());
		}
	}

	public static void flushAndClear(){
		OutputStream bos = CSVDataHolder.getOut();
		try {
			if (bos != null) {
				bos.flush();
			}
		} catch (Exception e) {
			logger.error("close error", e);
		}finally{
			CSVDataHolder.clear();
		}
	}

	public static void flushAndClose(){
		OutputStream bos = CSVDataHolder.getOut();
		try {
			if (bos != null) {
				bos.flush();
			}
		} catch (Exception e) {
			logger.error("close error", e);
		}finally{
			IOUtils.closeQuietly(bos);
			CSVDataHolder.clear();
		}
	}
}
