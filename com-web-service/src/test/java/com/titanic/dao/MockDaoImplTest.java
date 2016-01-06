package com.titanic.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.stubVoid;
import static org.mockito.Mockito.when;

/**
 *mock 测试
 */
@RunWith(MockitoJUnitRunner.class)
public class MockDaoImplTest
{
    @Mock
    MockDaoImpl mockDaoImpl;


    @Test
    public void init()
    {
        when(mockDaoImpl.getData()).thenReturn("ssss");
    }


    /**
     * 无参，void返回类型
     * @throws Exception
     */
    @Test
    public void testGetData() throws Exception
    {
        mockDaoImpl.getData();
    }

    @Test
    public void testInit() throws Exception
    {
//        stubVoid(mockDaoImpl).toReturn().on().init();
//        mockDaoImpl.init();
    }
}