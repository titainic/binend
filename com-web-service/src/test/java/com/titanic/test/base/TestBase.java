
package com.titanic.test.base;

import com.titanic.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


/**
 * 测试基类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/test/spring-web-service-test.xml"})
public class TestBase
{

    @Resource
    DemoService demoService;

    @Test
    public void test()
    {
        demoService.test();
        System.out.println("TestBase");
    }

}

