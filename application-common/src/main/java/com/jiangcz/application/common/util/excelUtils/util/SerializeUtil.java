package com.jiangcz.application.common.util.excelUtils.util;


import com.jiangcz.application.common.util.excelUtils.exception.ExceptionUtil;

import java.io.*;

/**
 * 对象序列化工具
 * @author chennina
 *
 */
public class SerializeUtil {

	/**
	 * 序列化对象为byte
	 * @param value 对象
	 * @return byte[]
	 */
	public byte[] serializeObject(Object value) {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(value);
			byte[] ret = bos.toByteArray();
			return ret;
		} catch (IOException e) {
			e.printStackTrace();
			ExceptionUtil.wrapperException(e);
			return null;
		} finally {
			try {

				if (null != oos)
					oos.close();
			} catch (IOException e) {
				e.printStackTrace();
				ExceptionUtil.wrapperException(e);

			}
		}
	}

	/**
	 * 反序列化对象
	 * @param objbytes 序列化Byte[]
	 * @return 对象
	 */
	public Object unserializeObject(byte[] objbytes) {
		
		ByteArrayInputStream bais = new ByteArrayInputStream(objbytes);
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(bais);
			Object ret =  ois.readObject();
			return ret;
		} catch (Exception e) {

			e.printStackTrace();
			ExceptionUtil.wrapperException(e);
			return null;

		} finally {
			try {

				if (null != ois)
					ois.close();

			} catch (Exception e) {
				e.printStackTrace();
				ExceptionUtil.wrapperException(e);

			}

		}
	}

}
