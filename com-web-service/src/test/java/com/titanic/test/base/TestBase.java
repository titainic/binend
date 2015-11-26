
package com.titanic.test.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




/**
 * 测试基类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/test/spring-web-service-test.xml"})
public class TestBase
{

}

