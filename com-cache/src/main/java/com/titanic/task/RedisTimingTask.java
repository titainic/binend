package com.titanic.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.titanic.conf.RedisCacheUtilSetting;

/**
 * redis定时调度任务。每天晚上12点，定时清空所有redis数据库中的所有缓存数据
 * 
 * @author titanic
 *
 */
@Component
@Lazy(false)
public class RedisTimingTask
{
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	RedisCacheUtilSetting redis;

	/**
	 * 每天晚上12点定时触发清理redis缓存功能
	 */
	@Scheduled(cron = "0 0 0 * * ? ")
	public void clearRedisCache()
	{
		log.info("redis 开始清除所有缓存中的数据");
		redis.dellAll();
	}

	// @Scheduled(cron="0/5 * *  * * ? ")
	// public void info()
	// {
	// System.out.println();
	// log.info("---定时调度任务");
	// log.info("向来难熬，奈何加班！");
	// }

	int count = 0;

	@Scheduled(cron = "0/10 * *  * * ? ")
	public void titanic()
	{
		if (count ==0 )
		{
			log.info(" 老师:为什么月球上那么多环形山？");
			count++;
		}

	}

	@Scheduled(cron = "0/15 * *  * * ? ")
	public void titanic2()
	{
		if (count == 1)
		{
			log.info(" 小明:老师，那是坑");
			count++;
		}

	}

	@Scheduled(cron = "0/20 * *  * * ? ")
	public void titanic3()
	{
		if (count == 2)
		{
			log.info(" 老师:也对，为什么那么多坑？");
			count++;
		}

	}

	@Scheduled(cron = "0/25 * *  * * ? ")
	public void titanic4()
	{
		if (count == 3)
		{
			log.info(" 小明:因为嫦娥在学挖掘机");
			count++;

		}
	}




}
