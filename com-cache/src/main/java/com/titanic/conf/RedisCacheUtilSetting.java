package com.titanic.conf;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis具体操作
 * 
 * @author titanic
 *
 */
@Component
public class RedisCacheUtilSetting
{
	@Resource
	JedisPool jedisPool;

	/**
	 * 把对象放入Hash中
	 */
	public void hset(String key, String field, Object o)
	{
		Jedis jedis = jedisPool.getResource();
		jedis.hset(key, field, JSON.toJSONString(o));
		jedisPool.returnResource(jedis);
	}

	/**
	 * 删除keys对应的记录,可以是多个key
	 *
	 * @param String
	 *            ... keys
	 * @return 删除的记录数
	 * */
	public long del(String... keys)
	{
		Jedis jedis = jedisPool.getResource();
		long count = jedis.del(keys);
		jedisPool.returnResource(jedis);
		return count;
	}

	/**
	 * 从Hash中获取对象
	 */
	public String hget(String key, String field)
	{
		Jedis jedis = jedisPool.getResource();
		String text = jedis.hget(key, field);
		jedisPool.returnResource(jedis);
		return text;
	}

	/**
	 * 从Hash中获取对象,转换成制定类型
	 */
	public <T> T hget(String key, String field, Class<T> clazz)
	{
		String text = hget(key, field);
		T result = JSON.parseObject(text, clazz);
		return result;
	}

	/**
	 * 从Hash中删除对象
	 */
	public void hdel(String key, String... field)
	{
		Jedis jedis = jedisPool.getResource();
		Object result = jedis.hdel(key, field);
		jedisPool.returnResource(jedis);
	}

	/**
	 * 查询key的过期时间
	 *
	 * @param String
	 *            key
	 * @return 以秒为单位的时间表示
	 * */
	public long ttl(String key)
	{
		// ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = jedisPool.getResource();
		long len = sjedis.ttl(key);
		jedisPool.returnResource(sjedis);
		return len;
	}

	/**
	 * 设置key的过期时间，以秒为单位
	 *
	 * @param String
	 *            key
	 * @param 时间
	 *            ,已秒为单位
	 * @return 影响的记录数
	 * */
	public long expire(String key, int seconds)
	{
		Jedis sjedis = jedisPool.getResource();
		long count = sjedis.expire(key, seconds);
		jedisPool.returnResource(sjedis);
		return count;
	}
	
	/**
	 * 定时调度任务，清除redis里面所有数据
	 * @return
	 */
	public String dellAll()
	{
		Jedis sjedis = jedisPool.getResource();
		String count = sjedis.flushAll();
		jedisPool.returnResource(sjedis);
		return count;
	}

}
