package com.titanic.test.mock;

import com.titanic.dao.MockDao;
import com.titanic.service.MockService;
import com.titanic.test.base.TestBase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnit44Runner;

import static org.mockito.Mockito.when;

/**
 * mock依赖测试
 */
@RunWith(MockitoJUnit44Runner.class)
public class Mock02 //extends TestBase
{

    @Mock
    MockDao mockDao;

    @InjectMocks
    MockService mockService;

    @Before
    public void initTestMock()
    {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void mockServerDependencyDao()
    {
        when(mockDao.getData()).thenReturn("mock");

        String dao = mockService.getData();
        System.out.println(dao);
    }
}
