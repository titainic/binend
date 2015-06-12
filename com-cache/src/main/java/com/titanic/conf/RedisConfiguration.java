package com.titanic.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis链接配置
 * 
 * @author titanic
 *
 */
@Configuration
public class RedisConfiguration
{
	 @Value("${redis.maxIdle}")
	private int maxIdle ; // 最大连接数

	 @Value("${redis.maxTotal}")
	private int maxTotal  ; // 最小连接数

	 @Value("${redis.maxWaitMillis}")
	private int maxWaitMillis; // idle状态监测用异步线程evict进行检查，

	 @Value("${redis.testOnBorrow}")
	private boolean testOnBorrow  ; // 获得一个jedis实例的时候是否检查连接可用性

	 @Value("${redis.ip}")
	private String ip ; // redis服务器地址

	 @Value("${redis.port}")
	private int port ; // redis服务器连接端口

	@Bean
	public JedisPoolConfig jedisPoolConfig()
	{
		JedisPoolConfig jpc = new JedisPoolConfig();

		jpc.setMaxIdle(maxIdle);
		jpc.setMaxTotal(maxTotal);
		jpc.setMaxWaitMillis(maxWaitMillis);
		jpc.setTestOnBorrow(testOnBorrow);
		return jpc;
	}

	@Bean
	public JedisPool jedisPool()
	{
		JedisPool jp = new JedisPool(jedisPoolConfig(), ip, port);
		return jp;
	}
}
