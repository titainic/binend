package com.titanic.quartz.service;

import com.titanic.quartz.bean.ScheduleJob;
import org.quartz.*;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;


/**
 * 定时调度 实现服务
 */
@Service
public class QuartzService
{

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    public void execute()
    {
        List<ScheduleJob> jobList  = new ArrayList<ScheduleJob>();

        //此数据集也可从数据库获取
        for (int i = 0; i < 5; i++)
        {
            ScheduleJob job = new ScheduleJob();
            job.setJobId("10001" + i);
            job.setJobName("data_import" + i);
            job.setJobGroup("dataWork");
            job.setJobStatus("1");
            job.setCronExpression("0/5 * * * * ?");
            job.setDesc("数据导入任务");
            job.setJobClz("com.titanic.quartz.job.QuartzMyJob");
            jobList.add(job);
        }


        for(ScheduleJob job : jobList)
        {
            jobRun(job);
        }


    }

    private void jobRun(ScheduleJob job)
    {
        try
        {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();

            if(null!=job)
            {

                //获取触发器标识
                TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
                //获取触发器trigger
                CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

                if (null == trigger)
                {   //不存在任务

                    Class jobRun = Class.forName(job.getJobClz());
                    //创建任务
                    JobDetail jobDetail = JobBuilder.newJob(jobRun) .withIdentity(job.getJobName(), job.getJobGroup()).build();

                    jobDetail.getJobDataMap().put("scheduleJob", job);

                    //表达式调度构建器
                    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job .getCronExpression());

                    //按新的cronExpression表达式构建一个新的trigger
                    trigger = TriggerBuilder.newTrigger() .withIdentity(job.getJobName(), job.getJobGroup()) .withSchedule(scheduleBuilder) .build();

                    scheduler.scheduleJob(jobDetail, trigger);

                }
                else
                {//存在任务

                    // Trigger已存在，那么更新相应的定时设置
                    //表达式调度构建器
                    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job .getCronExpression());

                    //按新的cronExpression表达式重新构建trigger
                    trigger = trigger.getTriggerBuilder() .withIdentity(triggerKey) .withSchedule(scheduleBuilder) .build();

                    //按新的trigger重新设置job执行
                    scheduler.rescheduleJob(triggerKey, trigger);
                }
            }
        }
        catch (SchedulerException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
