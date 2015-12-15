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
public class ProducerQueueJob implements Job
{
    Logger log = Logger.getLogger(this.getClass());

    RamBufferQueueManager ramBufferQueueManager = RamBufferQueueManager.getInstance();

    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        RamQueue queue = ramBufferQueueManager.getQueue("RamBufferQueue");

        log.info("queue.offer 进行中");
        for(int x = 1 ; x <= 100 ; x++)
        {
            queue.offer(x + " - "+System.currentTimeMillis());
        }
        log.info("queue.offer 100 个数据完成"+queue.getSize());
    }
}
