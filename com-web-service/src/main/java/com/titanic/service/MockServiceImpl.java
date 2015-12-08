package com.titanic.service;

import com.titanic.dao.MockDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * mock测试类
 */
@Service
public class MockServiceImpl implements MockService
{

    @Resource
    MockDao mockDao;

    public String mockTest()
    {
        return "mock test";
    }

    public String getData()
    {
        String dao = mockDao.getData();
        return dao;
    }
}
