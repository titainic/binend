package com.titanic.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 定义spring线程池
 */
@Configuration
public class ThreadConfiguration
{
    /**
     * 定义spring线程池
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor taskExecutor()
    {
        ThreadPoolTaskExecutor tpte = new ThreadPoolTaskExecutor();
        tpte.setCorePoolSize(3); //活动线程
        tpte.setMaxPoolSize(5); //最大线程数
        tpte.setWaitForTasksToCompleteOnShutdown(true);
        return tpte;
    }

    /**
     * 定义队列bean
     * @return
     */
    @Bean
    public BlockingQueue blockingQueue()
    {
        BlockingQueue blockingQueue = new LinkedBlockingQueue(10000);
        return blockingQueue;
    }


}
