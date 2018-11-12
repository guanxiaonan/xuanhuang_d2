package com.dmx;

import com.dmx.material.service.impl.QuartzJob;
import com.dmx.material.utils.QuartzManager;
import org.quartz.*;

import java.util.List;

public class QuartzManagerTest {

    public static void main(String[] args) {
//
//        try {
////            System.out.println("测试 addJob");
////            QuartzManager.addCronJob(QuartzJob.class, "0/2 * * * * ?","jobName" ,
////                    "group1","tiggerName","triggerGroup1");
////
////            QuartzManager.addCronJob(QuartzJob.class, "0/3 * * * * ?","jobName" ,
////                    "tiggerName");
////            Thread.sleep(5000);
////            System.out.println("获取所有任务：");
////            Scheduler scheduler = QuartzManager.getScheduler();
////            Trigger.TriggerState tiggerName = scheduler.getTriggerState(new TriggerKey("tiggerName"));
////            System.out.println("tiggerName:"+ tiggerName);
////
////            Thread.sleep(5000);
////            System.out.println("移除定时任务:");
////            QuartzManager.removeJob("jobName","tiggerName");
////            QuartzManager.removeJob("jobName","group1","tiggerName","triggerGroup1");
//
//
//            System.out.println("再次添加定时任务:");
//            QuartzManager.addCronJob(QuartzJob.class, "0/3 * * * * ?","jobName" ,"g1",
//                    "triggerName","t1");
//
//            Thread.sleep(4000);
//
//            System.out.println("修改定时任务1:");
//            QuartzManager.modifyJobTime("0/5 * * * * ?","triggerName","t1");
//
//            System.out.println("再次添加定时任务:");
//            QuartzManager.addCronJob(QuartzJob.class, "0/1 * * * * ?","jobName02","triggerName");
//
//            Thread.sleep(4000);
//            System.out.println("修改定时任务2:");
//            QuartzManager.modifyJobTime("0/2 * * * * ?","triggerName");
//
//            List jobList = QuartzManager.getJobList();
//            System.out.println("jobList:" + jobList);
//
//            Thread.sleep(5000);
//            //System.out.println("移除定时任务 job");
//            //QuartzManager.removeJob("jobName02","triggerName");
//            //QuartzManager.removeJob("jobName","g1","triggerName","t1");
//
//            System.out.println("暂停触发器:");
//            QuartzManager.pauseTrigger("triggerName","t1");
//            QuartzManager.pauseTrigger("triggerName",null);
//
//            Thread.sleep(11000);
//            List jobList1 = QuartzManager.getJobList();
//            System.out.println("jobList1:" + jobList1);
//
//            Scheduler scheduler = QuartzManager.getScheduler();
//            Trigger.TriggerState triggerState = scheduler.getTriggerState(new TriggerKey("triggerName", "t1"));
//            Trigger.TriggerState triggerState2 = scheduler.getTriggerState(new TriggerKey("triggerName", null));
//
//            System.out.println("triggerState:"+triggerState);
//            System.out.println("triggerState2:"+triggerState2);
//
//            //Thread.sleep(10000);
////            System.out.println("关闭所有定时任务：");
////            QuartzManager.shutdownJobs();
////
////            Scheduler scheduler2 = QuartzManager.getScheduler();
////            Trigger.TriggerState triggerState3 = scheduler2.getTriggerState(new TriggerKey("triggerName", "t1"));
////            Trigger.TriggerState triggerState4 = scheduler2.getTriggerState(new TriggerKey("triggerName", null));
////
////            System.out.println("triggerState3:"+triggerState3);
////            System.out.println("triggerState4:"+triggerState4);
//
//            Thread.sleep(5000);
//            System.out.println("resume trigger：");
//            QuartzManager.resumeTrigger("triggerName", "t1");
//
//            Thread.sleep(7000);
//            Scheduler scheduler3 = QuartzManager.getScheduler();
//            Trigger.TriggerState triggerState5 = scheduler3.getTriggerState(new TriggerKey("triggerName", "t1"));
//            Trigger.TriggerState triggerState6 = scheduler3.getTriggerState(new TriggerKey("triggerName", null));
//
//            System.out.println("triggerState5:"+triggerState5);
//            System.out.println("triggerState6:"+triggerState6);
//
//            System.out.println("pauseJob:");
//            QuartzManager.pauseJob("jobName","g1");
//            Scheduler scheduler4 = QuartzManager.getScheduler();
//            Trigger.TriggerState triggerState4 = scheduler4.getTriggerState(new TriggerKey("triggerName", "t1"));
//            System.out.println("triggerState4:" + triggerState4);
//
//            Thread.sleep(5000);
//            System.out.println("resumeAll:");
//            QuartzManager.resumeAll();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
