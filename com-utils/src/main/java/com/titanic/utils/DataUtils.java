package com.titanic.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 * @author titanic
 *
 */
public class DataUtils
{
	public static String cstTime2String(String dateStr)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

		//java.util.Date对象
		Date date;
		String formatStr2 = null;
		try
		{
			date = (Date) sdf.parse(dateStr);
			String formatStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
			 formatStr2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return formatStr2;
	}
}
