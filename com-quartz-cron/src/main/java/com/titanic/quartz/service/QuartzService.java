package com.titanic.quartz.service;

import com.titanic.quartz.bean.ScheduleJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


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
//        List<ScheduleJob> jobList  = new ArrayList<ScheduleJob>();

        //此数据集也可从数据库获取

        ScheduleJob Producerjob = new ScheduleJob();
        Producerjob.setJobId("10001" );
        Producerjob.setJobName("queue.offer");
        Producerjob.setJobGroup("queue");
        Producerjob.setJobStatus("1");
        Producerjob.setCronExpression("0/3 * * * * ?");
        Producerjob.setDesc("数据导入任务");
        Producerjob.setJobClz("com.titanic.quartz.job.ProducerQueueJob");


        ScheduleJob ConsumerQueueJob =  new ScheduleJob();
        ConsumerQueueJob.setJobId("10001" );
        ConsumerQueueJob.setJobName("queue.take" );
        ConsumerQueueJob.setJobGroup("queue");
        ConsumerQueueJob.setJobStatus("1");
        ConsumerQueueJob.setCronExpression("0/5 * * * * ?");
        ConsumerQueueJob.setDesc("数据导入任务");
        ConsumerQueueJob.setJobClz("com.titanic.quartz.job.ConsumerQueueJob");

        ScheduleJob ConsumerQueueJob_2 =  new ScheduleJob();
        ConsumerQueueJob_2.setJobId("10001" );
        ConsumerQueueJob_2.setJobName("queue.take" );
        ConsumerQueueJob_2.setJobGroup("queue");
        ConsumerQueueJob_2.setJobStatus("1");
        ConsumerQueueJob_2.setCronExpression("0/5 * * * * ?");
        ConsumerQueueJob_2.setDesc("数据导入任务");
        ConsumerQueueJob_2.setJobClz("com.titanic.quartz.job.ConsumerQueueJob");


        jobRun(Producerjob);
        jobRun(ConsumerQueueJob);
        jobRun(ConsumerQueueJob_2);



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
