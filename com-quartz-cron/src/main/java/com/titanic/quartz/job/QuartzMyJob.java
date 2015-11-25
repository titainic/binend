package com.titanic.quartz.job;

import com.titanic.quartz.bean.ScheduleJob;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 自定义的job。
 * 定时调度 学习网站
 * http://www.dexcoder.com/selfly/article/308
 * http://linhongyu.blog.51cto.com/6373370/1530148
 *
 */
//@DisallowConcurrentExecution
public class QuartzMyJob implements Job
{
    Logger log = Logger.getLogger(this.getClass());

    public void execute(JobExecutionContext context) throws JobExecutionException
    {

        log.info("任务成功运行");
        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
        log.info("任务名称 = [" + scheduleJob.getJobName() + "]");

        //根据name 与 group组成的唯一标识来判别该执行何种操作……

    }
}
