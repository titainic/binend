package com.titanic.constants;

/**
 * 
 * 功能描述： 缓存相关的一些常量
 */

public class CacheConstants
{

	// 一分钟 60秒
	public static final int ONE_MINUTE_S = 60;

	// 一小时 3600秒
	public static final int ONE_HOUR_S = 60 * ONE_MINUTE_S;

	// 一天 24 小时
	public static final int ONE_DAY_S = 24 * ONE_HOUR_S;

	// 一秒的毫秒数
	public static final int MILIPERSECOND = 1000;

	// 一天的毫秒数
	public static final int MILIOFDAY = ONE_DAY_S * MILIPERSECOND;

	// 一分钟的毫秒数
	// 每分钟刷新一次
	public static final long FLUSH_ROUTER_INTERVAL = ONE_MINUTE_S
			* MILIPERSECOND;
}
