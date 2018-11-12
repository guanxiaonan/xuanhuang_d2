package com.dmx.material.service.impl;

import com.dmx.material.service.ProduceJob;
import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 任务执行类
 */
public class QuartzJob implements ProduceJob {

    @Override
    public void execute(JobExecutionContext arg0) {
        String jobName = null; // 获取jobName做业务处理
        try {
            jobName = arg0.getScheduler().getSchedulerName();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


        Scheduler scheduler = arg0.getScheduler();
        Trigger trigger = arg0.getTrigger();
        final JobKey jobKey = trigger.getJobKey();
        final TriggerKey key = trigger.getKey();
        Calendar calendar = arg0.getCalendar();
        Date fireTime = arg0.getFireTime();
        Job jobInstance = arg0.getJobInstance();
        long jobRunTime = arg0.getJobRunTime();
        JobDataMap mergedJobDataMap = arg0.getMergedJobDataMap();
        Date nextFireTime = arg0.getNextFireTime();
        int refireCount = arg0.getRefireCount();
        Date scheduledFireTime = arg0.getScheduledFireTime();
        Object result = arg0.getResult();

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+
                "★★★★★★★★★★★" + "jobKey:"+jobKey+";triggerKey:"+key);
    }
}
