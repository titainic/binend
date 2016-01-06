package com.titanic.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 *  spring 多线程 使用
 * spring 启动线程池
 */
@Component
public class ThreadTask
{

    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    @Resource
    private ThreadQueueIn threadQueueIn;


    @Resource
    private ThreadQueueOut threadQueueOut;



    @PostConstruct
    public void startThreadPool()
    {
        taskExecutor.execute(threadQueueIn);

        taskExecutor.execute(threadQueueOut);
        taskExecutor.execute(threadQueueOut);

    }
}
