/**
 * 当当网内部系统开发部订单组JAVA基础类库
 * Author zhangjing@dangdang.com
 * Copyright (c) DangDang Corporation.  All rights reserved.
 */
package com.jiangcz.application.common.util.excelUtils.util;


import com.jiangcz.application.common.util.excelUtils.exception.ExceptionUtil;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 使用Get或Post进行http请求工具类
*
 */
public class HttpUtil {
	/**
	 * 重定向 需要在web项目环境下使用
	* @param url 重定向至的url
	 */
/*	public static void redirect(String url)
	{
		try
		{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.sendRedirect(url);			
		}
		catch(Exception ex)
		{
			LogWriter.writeErrorLog(ex);
		}
	}*/
	/**
	 * 使用get方式发起http请求,使用系统默认编码,写access log,使用http_connection_timeout参数指定的超时时间,若没有该配置,则没有超时时间
	* @param url 请求地址
	* @return http响应结果,出现异常时返回空字符串
	 */
	public static String getPageHTML(String url)
	{
		return getPageHTML(url, Charset.defaultCharset(), 0);
	}
	/**
	 * 使用get方式发起http请求,使用http_connection_timeout参数指定的超时时间,若没有该配置,则没有超时时间
	* @param url url 请求地址
	* @param charset http请求的编码
	* @return http响应结果,出现异常时返回空字符串
	 */
	public static String getPageHTML(String url,Charset charset)
	{
		return getPageHTML(url, charset, 0);
	}
	/**
	 * 使用get方式发起http请求
	* @param url 请求地址
	* @param charset http请求的编码
	* @param http_connection_timeout 以毫秒为单位的超时时间
	* @return http响应结果,出现异常时返回空字符串
	 */
	public static String getPageHTML(String url,Charset charset, int http_connection_timeout)
	{
		try
		{
			HttpParams httpparams = new BasicHttpParams();
			httpparams.setParameter(CoreConnectionPNames.SO_TIMEOUT, http_connection_timeout);
			HttpClient httpclient = new DefaultHttpClient(httpparams);
			httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
			HttpGet httpget = new HttpGet(url);
			httpget.addHeader("Connection", "close");
			HttpResponse response = httpclient.execute(httpget);
			InputStream is = response.getEntity().getContent(); 
			BufferedInputStream bis = new BufferedInputStream(is);
			ByteArrayBuffer baf = new ByteArrayBuffer(20);
			int current = 0; 
			while((current = bis.read()) != -1)
			{ 
				baf.append((byte)current); 
			} 
			String result = new String(baf.toByteArray(),charset);
			bis.close();
			is.close();
			return result;
		}
		catch(Exception ex)
		{
			throw ExceptionUtil.wrapperException(ex);
		}
	}
	/**
	 * 使用post方式发起http请求,使用http_connection_timeout参数指定的超时时间,若没有该配置,则没有超时时间
	* @param url url 请求地址
	* @param params Map表示的http请求参数
	* @param charset charset http请求的编码
	* @return http响应结果,出现异常时返回空字符串
	 */
	public static String getPageHTML(String url, Map<String, String> params,Charset charset)
	{
		return getPageHTML(url,params,charset, 0);
	}
	/**
	 * 使用post方式发起http请求
	* @param url url 请求地址
	* @param params Map表示的http请求参数
	* @param charset charset http请求的编码
	* @param
	* @param http_connection_timeout 以毫秒为单位的超时时间
	* @return http响应结果,出现异常时返回空字符串
	 */
	public static String getPageHTML(String url, Map<String, String> params,Charset charset, int http_connection_timeout)
	{
		String paramString = "";
		try
		{
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			Set set = params.entrySet();
			StringBuilder sb = new StringBuilder();
			for(Iterator iter = set.iterator(); iter.hasNext();)
			{
			   Map.Entry entry = (Map.Entry)iter.next();  
			   String key = (String)entry.getKey();
			   String value = (String)entry.getValue();
			   nameValuePairs.add(new BasicNameValuePair(key, value));
			   sb.append(String.format("{0}={1}", key, value));
			   sb.append("&");
			}
			paramString = sb.toString();
			if(paramString.endsWith("&"))
			{
				paramString = paramString.substring(0, paramString.length()-1);
			}
			HttpParams httpparams = new BasicHttpParams();
			httpparams.setParameter(CoreConnectionPNames.SO_TIMEOUT, http_connection_timeout);
			HttpClient httpclient = new DefaultHttpClient(httpparams);
			httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
			HttpPost httppost = new HttpPost(url);
			httppost.addHeader("Connection", "close");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, charset));
			HttpResponse response = httpclient.execute(httppost);
			InputStream is = response.getEntity().getContent(); 
			BufferedInputStream bis = new BufferedInputStream(is);
			ByteArrayBuffer baf = new ByteArrayBuffer(20);
			int current = 0; 
			while((current = bis.read()) != -1)
			{ 
				baf.append((byte)current); 
			} 
			String result = new String(baf.toByteArray(),charset);
			bis.close();
			is.close();	
			return result;
		}
		catch(Exception ex)
		{
			throw ExceptionUtil.wrapperException(ex);
		}
	}
	/**
	 * 使用post方式发起http请求,,使用http_connection_timeout参数指定的超时时间,若没有该配置,则没有超时时间
	* @param url url 请求地址
	* @param params String表示的http请求参数
	* @param charset charset http请求的编码
	* @return http响应结果,出现异常时返回空字符串
	 */
	public static String getPageHTML(String url, String params,Charset charset)
	{
		return getPageHTML(url,params,charset, 0);
	}
	/**
	 * 使用post方式发起http请求
	* @param url url 请求地址
	* @param params String表示的http请求参数
	* @param charset charset http请求的编码
	* @param
	* @param http_connection_timeout 以毫秒为单位的超时时间
	* @return http响应结果,出现异常时返回空字符串
	 */
	public static String getPageHTML(String url, String params,Charset charset, int http_connection_timeout)
	{
		try
		{
			HttpParams httpparams = new BasicHttpParams();
			httpparams.setParameter(CoreConnectionPNames.SO_TIMEOUT, http_connection_timeout);
			HttpClient httpclient = new DefaultHttpClient(httpparams);
			httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
			HttpPost httppost = new HttpPost(url);
			httppost.addHeader("Connection", "close");
			StringEntity se = new StringEntity(params, ContentType.create("text/plain", charset.name()));
			httppost.setEntity(se); 
			HttpResponse response = httpclient.execute(httppost);
			InputStream is = response.getEntity().getContent(); 
			BufferedInputStream bis = new BufferedInputStream(is);
			ByteArrayBuffer baf = new ByteArrayBuffer(20);
			int current = 0; 
			while((current = bis.read()) != -1)
			{ 
				baf.append((byte)current); 
			} 
			String result = new String(baf.toByteArray(),charset);
			bis.close();
			is.close();
			return result;
		}
		catch(Exception ex)
		{
			throw ExceptionUtil.wrapperException(ex);
		}
	}
}
