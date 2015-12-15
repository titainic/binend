package com.titanic.quartz.job;

import com.titanic.quartz.queue.RamBufferQueueManager;
import com.titanic.quartz.queue.RamQueue;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by wb-yangbin.d on 2015/12/8.
 */
@Component
public class ConsumerQueueJob implements Job
{
    Logger log = Logger.getLogger(this.getClass());


    RamBufferQueueManager ramBufferQueueManager = RamBufferQueueManager.getInstance();


    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        RamQueue queue = ramBufferQueueManager.getQueue("RamBufferQueue");

        while(!queue.isEmpty())
        {
            String str = null;
            try
            {
                str = (String) queue.take();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            log.info("queue.take()" + " - "+str + Thread.currentThread().getName()+"--"+queue.getSize());
        }
    }
}
