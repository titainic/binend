package com.titanic.ftp;

import org.apache.camel.CamelContext;
import org.apache.camel.Routes;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * 移动目录
 */
public class TestRun
{
    private static String fromUrl = "file:D:\\titanic";
    //    private static String toUrl = "ftp://10.125.1.94:21/camel?username=guanjing.pangj&password=Willam2004";
    private static String toUrl = "file:D:\\tmp\\";

    public static void main(String[] args) throws Exception
    {
        CamelContext context = new DefaultCamelContext();
        Routes routes = new RouteBuilder()
        {
            @Override
            public void configure() throws Exception
            {
                from(fromUrl).to(toUrl);
            }
        };

        context.addRoutes(routes);
        context.start();
    }
}
