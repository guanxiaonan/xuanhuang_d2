package com.dmx.material.init;

import com.dmx.material.pojo.Task;
import com.dmx.material.service.TaskService;
import com.dmx.material.service.impl.CheckNewTaskJob;
import com.dmx.material.utils.EmailUtil;
import com.dmx.material.utils.QuartzManager;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 程序启动后,开启定时检查任务
 * 这里通过设定value的值来指定执行顺序
 */
@Component
@Order(value = 1)
public class MyApplicationRunner implements ApplicationRunner{

    private static Logger logger = LoggerFactory.getLogger(MyApplicationRunner.class);

    //时间间隔,定时检查数据库中是否有新任务
    @Value("${task.check.interval.in.second}")
    private String checkNewTaskIntervalSecond;

    @Autowired
    QuartzManager quartzManager;

    @Autowired
    TaskService taskService;

    @Override
    public void run(ApplicationArguments var1) {
        // 应用启动时,从数据库中将状态为RUNNING:3状态的定时任务取出,继续开始执行
        List<Task> runningTaskList = taskService.getRunningTaskList();
        taskService.addCronJobWithProduceId(runningTaskList);


        // 定时检查是否有新建的生产任务
        // 检查定时时间间隔
        if (Strings.isNullOrEmpty(checkNewTaskIntervalSecond) ||
                Integer.parseInt(checkNewTaskIntervalSecond) <= 0) {
            logger.error("task.check.interval.in.second={}",checkNewTaskIntervalSecond);
            return;
        }
        int interval = Integer.parseInt(checkNewTaskIntervalSecond);
        quartzManager.addSimpleJob(CheckNewTaskJob.class,
                interval,
                "checkJob",
                "group1",
                "trigger1",
                "triggerGroupName1");
    }
}
