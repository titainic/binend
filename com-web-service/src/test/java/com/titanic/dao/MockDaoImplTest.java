package com.titanic.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.when;

/**
 *PowerMock
 * https://my.oschina.net/u/1433482/blog/645155
 */
@RunWith(PowerMockRunner.class)
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