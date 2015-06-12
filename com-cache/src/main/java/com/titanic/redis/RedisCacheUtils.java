package com.titanic.redis;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.titanic.conf.RedisCacheUtilSetting;
import com.titanic.constants.CacheConstants;

/**
 * Redis缓存工具类.
 * 
 */
@Component
public class RedisCacheUtils
{
	/**
	 * 日期格式
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private GsonBuilder gsonBuilder = new GsonBuilder();

	private Gson gson;

	Logger log = Logger.getLogger(this.getClass());


	@Autowired
	private RedisCacheUtilSetting cacheClient;

	/**
	 * 
	 * 功能描述：清除缓存
	 * 
	 * @param 参数说明
	 *            返回值: 类型 <说明>
	 * @return 返回值
	 * @throw 异常描述
	 * @see 需要参见的其它内容
	 */
	public void hdel(String key, String field)
	{
		this.cacheClient.hdel(key, field);
	}

	/**
	 * 
	 * 功能描述：清除缓存
	 * 
	 * @param 参数说明
	 *            返回值: 类型 <说明>
	 * @return 返回值
	 * @throw 异常描述
	 * @see 需要参见的其它内容
	 */
	public void del(String key)
	{
		this.cacheClient.del(key);
	}

	/**
	 * 将序列化对象值value关联到key， 如果key已经持有其他值，SET就覆写旧值
	 * 
	 * @param key
	 *            缓存key
	 * @param value
	 *            缓存的对象
	 */
	public void hset(String key, String field, Object value)
	{
		this.hset(key, field, value, CacheConstants.ONE_DAY_S);
	}

	public void hset(String key, String field, Object value, int expireTime)
	{
		// 缓存管理器中取出缓存配置
		try
		{
			gson = gsonBuilder.setDateFormat(DATE_FORMAT)
					.enableComplexMapKeySerialization().create();

			boolean exist = true;

			long leftTime = cacheClient.ttl(key);
			if (leftTime < 0)
			{
				// 过期时间小于0说明key不存在
				exist = false;
			}
			// 缓存配置不为空且有效则设置业务缓存
			cacheClient.hset(key, field, gson.toJson(value));

			if (!exist)
			{
				// 有效时间不为0且key为新插入则设置业务缓存的有效时间
				cacheClient.expire(key, expireTime);
			}
		} catch (Exception e)
		{
			log.error(e);
		}
	}

	/**
	 * 返回哈希表key中给定域field的值
	 * 
	 * @param key
	 *            缓存key
	 * @param field
	 *            key中的域
	 * @param type
	 *            被缓存的对象类型
	 * @return T 被缓存的对象
	 */
	public <T> T hget(String key, String field, Class<T> type)
	{
		// 缓存管理器中取出缓存配置
		try
		{
			gson = gsonBuilder.setDateFormat(DATE_FORMAT)
					.enableComplexMapKeySerialization().create();
			// 缓存配置不为空且有效则获取业务缓存
			String str = cacheClient.hget(key, field);
			if (StringUtils.isBlank(str))
			{
				return null;
			}
			// 转换成需要的对象
			return gson.fromJson(str, type);

		} catch (Exception e)
		{
			// logger.logException(e);
			return null;
		}
	}
}
