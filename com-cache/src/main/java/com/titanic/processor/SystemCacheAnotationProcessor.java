package com.titanic.processor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.titanic.redis.RedisCacheUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import com.titanic.annotation.RedisCache;
import com.titanic.constants.CacheCollection;

/**
 * redis缓存实现切面
 * @author titanic
 *
 */
@Aspect
@Component
public class SystemCacheAnotationProcessor
{
	Logger log = Logger.getLogger(this.getClass());

	private static String CONNECTOR = "_";

	@Autowired
	RedisCacheUtils redisCacheUtils;

	@Around("@annotation(com.titanic.annotation.RedisCache)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable
	{
		Object methodReturnValue = null;
		try
		{
			Object[] args = joinPoint.getArgs();

			Signature signature = joinPoint.getSignature();

			if (signature instanceof MethodSignature)
			{
				MethodSignature methodSignature = (MethodSignature) signature;

				//获取方法
				Method aopMethod = methodSignature.getMethod();
				
				RedisCache cacheParam = (RedisCache) aopMethod .getAnnotation(RedisCache.class);

				// 这些field 最终要组成一个总的field。类似联合主键
				int[] splittedField = cacheParam.fieldIndex();
				String redisKey = cacheParam.key();
				Class<?> type = cacheParam.type();
				// 过期时间
				int expiredTime = cacheParam.expired();
				
				String unionedFiled = this.buildField(args, splittedField);

				Class<?> gsonType = null;
				if (cacheParam.collectionType().equals(CacheCollection.NONE))
				{
					gsonType = type;
				} else
				{
					gsonType = List.class;
				}

				Object cachedResult = redisCacheUtils.hget(redisKey, unionedFiled, gsonType);

				// 无缓存内容
				if (null == cachedResult && !cacheParam.ignoreDb())
				{
					// joinPoint.proceed();执行目标方法
					methodReturnValue = joinPoint.proceed();
					redisCacheUtils.hset(redisKey, unionedFiled, methodReturnValue, expiredTime);
				} 
				else
				{
					methodReturnValue = this.afterQueryCache(cachedResult, type, cacheParam.collectionType());
				}

			}
			else
			{
				methodReturnValue = joinPoint.proceed();
			}

		} catch (Exception e)
		{
			throw e;
		}
		return methodReturnValue;
	}

	@SuppressWarnings("unchecked")
	private List<Object> iteratorCollection(Class<?> type, Iterator<Object> itr)
	{
		List<Object> returnMethodResult = new ArrayList<Object>();
		while (itr.hasNext())
		{
			try
			{
				Object singleCacheBean = itr.next();
				Object obj = type.newInstance();

				DateTimeConverter dtConverter = new DateConverter();
				dtConverter.setPattern(RedisCacheUtils.DATE_FORMAT);
				ConvertUtils.register(dtConverter, java.util.Date.class);

				BeanUtils.populate(obj, (Map<String, String>) singleCacheBean);
				returnMethodResult.add(obj);
			} catch (Exception e)
			{
				log.error(e);
			}
		}
		return returnMethodResult;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object afterQueryCache(Object cachedResult, Class<?> type, CacheCollection collectionType)
	{
		Object returnCache = null;
		if (CacheCollection.LIST.equals(collectionType))
		{
			List<Object> prasedObject = this.iteratorCollection(type, ((List<Object>) cachedResult).iterator());
			// 已经是list了
			returnCache = prasedObject;
		} else if (CacheCollection.SET.equals(collectionType))
		{
			List<Object> prasedObject = this.iteratorCollection(type,
					((List<Object>) cachedResult).iterator());
			returnCache = new HashSet(prasedObject);
		} else
		{
			returnCache = cachedResult;
		}
		return returnCache;
	}

	/**
	 * 
	 * 功能描述：将 分开的 值合成一个 值。eg: 123 ,abc,1ab->123_abc_1ab
	 * 
	 * @param 参数说明
	 *            返回值: 类型 <说明>
	 * @return 返回值
	 * @throw 异常描述
	 * @see 需要参见的其它内容
	 */
	private String buildField(Object[] args, int[] fields)
	{
		StringBuilder sb = new StringBuilder();
		if (null != fields)
		{
			for (int i = 0; i < fields.length; i++)
			{
				Object arg = args[fields[i]];
				if (ClassUtils.isPrimitiveOrWrapper(arg.getClass()) || arg instanceof String)
				{
					sb.append(arg.toString()).append(CONNECTOR);
				} else
				{
					throw new RuntimeException( "非法的 redis field.Field 只能为 基本类型或String");
				}
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
