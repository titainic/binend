package com.titanic.thread;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;

/**
 * 多实例消费线程
 */
@Scope("prototype")
@Component
public class ThreadQueueOut implements Runnable
{

    @Resource
    private BlockingQueue<String> blockingQueue;

    public void run()
    {
        for (;;)
        {
            try
            {
                String queueMsg =  blockingQueue.take();
//                System.out.println(Thread.currentThread().getName()+"  : "+queueMsg);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }
}
