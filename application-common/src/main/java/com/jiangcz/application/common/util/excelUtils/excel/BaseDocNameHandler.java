package com.jiangcz.application.common.util.excelUtils.excel;




import com.jiangcz.application.common.util.excelUtils.excel.basedoc.IBaseDoc;
import com.jiangcz.application.common.util.excelUtils.excel.basedoc.IBaseDocLoader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;


@Slf4j
public class BaseDocNameHandler<T, B extends IBaseDoc> implements
		IExcelValueHandler<T> {

	private IBaseDocLoader<B> loader;

	public BaseDocNameHandler(IBaseDocLoader<B> loader) {
		this.loader = loader;
	}

	@Override
	public String getValue(T obj, ExcelColumn column) {

		String idField = column.getRefIdField();
		if (StringUtils.isBlank(idField)) {
			return "";
		}
		try {
			B basedoc = null;
			if (idField.indexOf("|") < 0) {
				String id = BeanUtils.getProperty(obj, idField);
				if(id==null){
					return "";
				}
				basedoc = this.loader.getByKey(id);
			} else {
				String[] idFields = idField.split("\\|");
				String[] ids = new String[idFields.length];
				for (int i = 0; i < ids.length; i++) {
					String temp = BeanUtils.getProperty(obj, idFields[i]);
					ids[i] = temp;	
				}
				basedoc = this.loader.getByKey(ids);
			}
			if (basedoc == null) {
				return "";
			}
			return BeanUtils.getProperty(basedoc, basedoc.getNameField());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";

	}

}
