package com.dmx.material.utils;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 定时任务管理类
 */
@Component
public class QuartzManager {
    //private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    @Autowired
    private Scheduler scheduler;

    private static Logger logger = LoggerFactory.getLogger(QuartzManager.class);
    /**
     * 添加一个定时任务
     * @param jobClass jobClass对象
     * @param time cron 表达式
     * @param jobName 任务名
     * @param jobGroupName 任务组名
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     */
    public void addCronJob(Class jobClass, String time, String jobName,
                              String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            //Scheduler sched = schedulerFactory.getScheduler();
            //JobDetail jobDetail = new JobDetailImpl(jobName, jobGroupName, cls);// 任务名，任务组，任务执行类
            JobDetail jobDetail = newJob(jobClass)
                    .withIdentity(jobName, jobGroupName)
                    .build();

            // Trigger the job to run now, and then every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity(triggerName, triggerGroupName)
                    .startNow()
                    .withSchedule(cronSchedule(time))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加一个定时任务,传入生产业务id
     * @param jobClass jobClass对象
     * @param time cron 表达式
     * @param jobName 任务名
     * @param jobGroupName 任务组名
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     * @param taskId 任务id(业务)
     * @param produceId 生产业务id
     */
    public void addCronJobWithProduceId(Class jobClass, String time, String jobName,
                                        String jobGroupName, String triggerName, String triggerGroupName, String produceId,Long taskId) {
        try {
            JobDetail jobDetail = newJob(jobClass)
                    .withIdentity(jobName, jobGroupName)
                    .usingJobData("produceId",produceId)
                    .usingJobData("taskId",taskId)
                    .build();

            // Trigger the job to run now, and then every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity(triggerName, triggerGroupName)
                    .startNow()
                    .withSchedule(cronSchedule(time))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * 添加一个Simple定时任务
     * @param jobClass jobClass对象
     * @param seconds 时间间隔[s: 秒]
     * @param jobName 任务名
     * @param jobGroupName 任务组名
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     */
    public void addSimpleJob(Class jobClass, int seconds, String jobName,
                                  String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            //Scheduler sched = schedulerFactory.getScheduler();
            JobDetail jobDetail = newJob(jobClass)
                    .withIdentity(jobName, jobGroupName)
                    .build();

            // Trigger the job to run now
            Trigger trigger = newTrigger()
                    .withIdentity(triggerName, triggerGroupName)
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .repeatForever()
                            .withIntervalInSeconds(seconds))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加一个Simple定时任务,只执行一次
     * @param jobClass jobClass对象
     * @param jobName 任务名
     * @param jobGroupName 任务组名
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     */
    public void addSimpleJobExcuteOnce(Class jobClass,String jobName,
                             String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            JobDetail jobDetail = newJob(jobClass)
                    .withIdentity(jobName, jobGroupName)
                    .build();

            // Trigger the job to run now
            Trigger trigger = newTrigger()
                    .withIdentity(triggerName, triggerGroupName)
                    .startNow()
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 添加一个定时任务,使用默认的任务组名,触发器组名
     * @param jobClass 任务
     * @param time 时间设置，参考quartz说明文档
     * @param jobName 任务名
     * @param triggerName 触发器名
     */
    public void addCronJob(Class jobClass,String time, String jobName, String triggerName) {
        addCronJob(jobClass,time,jobName,null,triggerName,null);
    }

    /**
     * 修改cron触发器的定时任务
     * @param triggerName 触发器名
     * @param time cron 表达式
     */
    public void modifyJobTime(String time, String triggerName) {
        modifyJobTime(time, triggerName,null);
    }

    /**
     * 修改cron触发器的定时任务
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     * @param time cron 表达式
     */
    public void modifyJobTime(String time, String triggerName, String triggerGroupName) {
        try {
            //Scheduler sched = schedulerFactory.getScheduler();

            TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);
            // 检查是否存在 trigger
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                logger.warn("trigger is null,please check triggerName and triggerGroupName");
                return;
            }
            final String oldCron = trigger.getCronExpression();
            // 修改 trigger, 替换原来的
            if (!oldCron.equals(time)) {
                scheduler.rescheduleJob(triggerKey, TriggerBuilder.
                        newTrigger().
                        withIdentity(triggerName,triggerGroupName).
                        withSchedule(cronSchedule(time)).
                        build());
                logger.info("update trigger schedule success,old:[{}],new:[{}]",oldCron,time);
            } else {
                logger.info("oldCron is equals newCron,dose not need to update");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 移除一个任务
     * @param jobName
     * @param jobGroupName
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组
     */
    public void removeJob(String jobName, String jobGroupName,String triggerName, String triggerGroupName) {
        try {
            //Scheduler sched = schedulerFactory.getScheduler();
            TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(new JobKey(jobName,jobGroupName));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 移除一个任务,使用默认的job group 和 trigger group (DEFAULT)
     * @param jobName   任务名称
     * @param triggerName  触发器名称
     */
    public void removeJob(String jobName, String triggerName) {
        try {
            //Scheduler sched = schedulerFactory.getScheduler();
            final TriggerKey triggerKey = new TriggerKey(triggerName);
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(new JobKey(jobName));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 启动所有定时任务
     */
    public void startJobs() {
        try {
            //Scheduler sched = schedulerFactory.getScheduler();
            if (!scheduler.isStarted()) {
                scheduler.start();
            } else {
                logger.info("定时任务已经启动....");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将 PAUSED 状态的触发器恢复正常
     * @param triggerName 触发器名称
     * @param triggerGroupName 触发器组名
     */
    public void resumeTrigger(String triggerName, String triggerGroupName) {
        try {
            //Scheduler sched = schedulerFactory.getScheduler();
            TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);
            if (Trigger.TriggerState.PAUSED.equals(scheduler.getTriggerState(triggerKey))) {
                scheduler.resumeTrigger(triggerKey);
                logger.info("trigger:{} is resumed",triggerKey);
            } else {
                logger.info("trigger:{} is not paused",triggerKey);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将 PAUSED 状态的触发器恢复正常
     * @param triggerName 触发器名称
     */
    public void resumeTrigger(String triggerName) {
        resumeTrigger(triggerName,null);
    }



            /**
             * 列出所有Quartz的作业
             * @return
             */
//    public static Scheduler getScheduler() {
//        Scheduler sched = null;
//        try {
//            scheduler =  schedulerFactory.getScheduler();
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//        return sched;
//    }


    /**
     * 获取所有job 列表
     * @return
     */
    public List getJobList() {
        //Scheduler sched = getScheduler();
        List<JobKey> jobList = null;
        try {
            jobList = new ArrayList<>();
            List<String> jobGroupNames = scheduler.getJobGroupNames();
            for (String groupName : jobGroupNames) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    jobList.add(jobKey);
//                    List<? extends Trigger> triggersOfJob = sched.getTriggersOfJob(jobKey);
//
//                    String jobName1 = jobKey.getName();
//                    String jobGroup1 = jobKey.getGroup();
//
//                    //get job's trigger
//                    List<Trigger> triggers = (List<Trigger>) sched.getTriggersOfJob(jobKey);
//                    Date nextFireTime = triggers.get(0).getNextFireTime();
//
//                    System.out.println("[jobName] : " + jobName1 + " [groupName] : "
//                            + jobGroup1 + " - " + nextFireTime);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    /**
     * 暂停 触发器
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     */
    public void pauseTrigger(String triggerName, String triggerGroupName) {
        TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);
        try {
            //Scheduler sched =  schedulerFactory.getScheduler();
            scheduler.pauseTrigger(triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止一个job任务,会将该任务关联的所有的触发器状态都设为 PAUSED
     * @param jobName
     * @param jobGroupName
     */
    public void pauseJob(String jobName, String jobGroupName){
        Scheduler sched;
        JobKey jobkey = new JobKey(jobName, jobGroupName);
        try {
            //sched = schedulerFactory.getScheduler();
            scheduler.pauseJob(jobkey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复相关的job任务
     * @param jobName
     * @param jobGroupName
     * @throws SchedulerException
     */
    public void resumeJob(String jobName, String jobGroupName) {
        Scheduler sched;
        JobKey jobkey = new JobKey(jobName, jobGroupName);
        try {
            //sched = schedulerFactory.getScheduler();
            scheduler.resumeJob(jobkey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭所有定时任务
     */
    public void shutdownJobs() {
        try {
            //Scheduler sched = schedulerFactory.getScheduler();
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 暂停调度中所有的job任务
     */
    public void pauseAll() {
        try {
           // Scheduler sched = schedulerFactory.getScheduler();
            if (!scheduler.isShutdown()) {
                scheduler.pauseAll();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 恢复调度中所有的job的任务
     */
    public void resumeAll() {
        try {
            //Scheduler sched = schedulerFactory.getScheduler();
            if (!scheduler.isShutdown()) {
                scheduler.resumeAll();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
