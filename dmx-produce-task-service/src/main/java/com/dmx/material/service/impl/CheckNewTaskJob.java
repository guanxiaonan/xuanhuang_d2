package com.dmx.material.service.impl;

import com.dmx.material.Constant.Constant;
import com.dmx.material.pojo.Task;
import com.dmx.material.pojo.Trigger;
import com.dmx.material.service.ProduceJob;
import com.dmx.material.service.TaskService;
import com.dmx.material.utils.EmailUtil;
import com.dmx.material.utils.QuartzManager;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 定时检查数据库中是否有新建的任务
 */
@Component
public class CheckNewTaskJob implements ProduceJob {

    private static Logger logger = LoggerFactory.getLogger(CheckNewTaskJob.class);

    @Autowired
    TaskService taskService;

    @Autowired
    QuartzManager quartzManager;

    private Long taskId;

    @Override
    public void execute(JobExecutionContext context) {

        List<Task> newTaskList = taskService.getNewTask();
        if (newTaskList.size() == 0) {
            logger.info("没有新创建的任务...");
        } else {
            taskService.addCronJobWithProduceId(newTaskList);
        }
    }
}
