package com.titanic.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;

/**
 * 忘记了.留这
 * @author titanic
 *
 */
public class HttpUtils 
{

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url)
	{

		StringBuilder result = new StringBuilder();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try
		{
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);

			try
			{
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				// 打印响应状态
				System.out.println(response.getStatusLine());

				result.append(StringUtilszz.inputStream2String(entity
						.getContent()));
			} finally
			{
				response.close();
			}
		} catch (ClientProtocolException e)
		{
			e.printStackTrace();
		} catch (ParseException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			// 关闭连接,释放资源
			try
			{
				httpclient.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return result.toString();
	}

	/**
	 * 发送ｐｏｓｔ请求。不完整，使用时，注意
	 * @param url
	 */
	public void post(String url)
	{
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("type", "house"));
		UrlEncodedFormEntity uefEntity;
		try
		{
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try
			{
				HttpEntity entity = response.getEntity();
				
			} finally
			{
				response.close();
			}
		} catch (ClientProtocolException e)
		{
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1)
		{
			e1.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			// 关闭连接,释放资源
			try
			{
				httpclient.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
