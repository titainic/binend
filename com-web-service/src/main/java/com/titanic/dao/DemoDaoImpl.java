package com.titanic.dao;

import com.titanic.annotation.RedisCache;
import com.titanic.constants.CacheConstants;

import java.util.List;

/**
 * Created by titanic on 15-6-12.
 */
public class DemoDaoImpl
{
    /**
     * 应该写在dao层.....
     *
     * redis缓存使用方法 Redis中的hash存储结构
     *
     * key:表示在redis中，hash数据结构中的表名（可以定义模块名称）
     *
     * fieldIndex：表示在redis中，hash里面key，fieldIndex = { 0, 1 }    //（0,1表示参数的个数，用来组合成fieldIndex中的key使用）
     *
     * type：要返回的实体
     *
     * expired：过期时间,时间的有效性
     *
     */
    @RedisCache(key = "test_test", fieldIndex = { 0, 1 }, type = Object.class, expired = CacheConstants.ONE_HOUR_S * 10)
    public List<Object> findCustomer(String name, int x)
    {

        return null;
    }
}
