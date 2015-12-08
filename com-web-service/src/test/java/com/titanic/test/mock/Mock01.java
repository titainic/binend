package com.titanic.test.mock;

import com.titanic.service.MockService;
import com.titanic.test.base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * mock测试
 */
public class Mock01 extends TestBase
{

    @Mock
    MockService mockService;


    @Before
    public void initMock()
    {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void mockServiceTest()
    {
        //设置方法的预返回值
        when(mockService.mockTest()).thenReturn("mock binend");
        String str = mockService.mockTest();
        System.out.println(str);
    }



    @Test
    public void interfaceMock()
    {
        //创建mock对象，参数可以是类，也可以是借口
        List<String> list = mock(List.class);

        //设置方法的预返回值
        when(list.get(0)).thenReturn("ssss");

        String result = list.get(0);

        //验证方法是否调用 .get(0)
        verify(list).get(0);

        //junit测试
        //Assert使用
        Assert.assertEquals("ssss",result);
    }

}
