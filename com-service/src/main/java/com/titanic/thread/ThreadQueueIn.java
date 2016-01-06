package com.titanic.thread;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;

/**
 * 入队列线程
 */
@Component
public class ThreadQueueIn implements Runnable
{
    @Resource
    private BlockingQueue<String> blockingQueue;

    public void run()
    {
        int count = 0;
        for (;;)
        {
            blockingQueue.offer(count+" : 消息->blockingQueue");


            count++;
        }


    }
}
