package com.titanic.quartz.run;

import com.titanic.quartz.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by wb-yangbin.d on 2015/11/25.
 */
@Component
@Lazy(false)
public class JobTestRun
{

    @Autowired
    QuartzService quartzService;

    @Scheduled(cron = "0/30 * *  * * ? ")
    public void titanic()
    {

        quartzService.execute();
    }
}
