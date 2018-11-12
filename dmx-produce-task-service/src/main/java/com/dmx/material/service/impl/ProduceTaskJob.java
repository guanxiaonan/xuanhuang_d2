package com.dmx.material.service.impl;

import com.dmx.material.Constant.Constant;
import com.dmx.material.pojo.ProduceRule;
import com.dmx.material.service.ProduceJob;
import com.dmx.material.service.TaskService;
import com.dmx.material.utils.QuartzManager;
import com.google.common.base.Strings;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 每日生产任务执行类
 */
public class ProduceTaskJob implements ProduceJob {

    private static Logger logger = LoggerFactory.getLogger(ProduceTaskJob.class);

    @Autowired
    TaskService taskService;

    @Autowired
    private QuartzManager quartzManager;

    private String jobName;
    private String jobGroupName;
    private String triggerName;
    private String triggerGroupName;

    @Override
    public void execute(JobExecutionContext context) {

        JobKey jobKey = context.getTrigger().getJobKey();
        jobName = jobKey.getName();
        jobGroupName = jobKey.getGroup();
        TriggerKey key = context.getTrigger().getKey();
        triggerName = key.getName();
        triggerGroupName = key.getGroup();

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String produceId = jobDataMap.getString("produceId");
        Long taskId = jobDataMap.getLong("taskId");
        if (!Strings.isNullOrEmpty(produceId)) {
            ProduceRule produceRule = taskService.getProduceRule(produceId);
            String inputMaterialAndNum = produceRule.getInputMaterialAndNum();
            String outputMaterial = produceRule.getOutputMaterial();
            Integer outputNum = produceRule.getOutputNum();

            String[] items = inputMaterialAndNum.split(",");
            Map<String,Long> produceInputMap = new HashMap();
            for (String item : items) {
               String inputMaterial = item.split(":")[0];
               Long inputMaterialNum = Long.parseLong(item.split(":")[1]);
               produceInputMap.put(inputMaterial,inputMaterialNum);
            }
            logger.info("生产计划,produceId:{},produceInputMap:{}",produceId,produceInputMap);
            final boolean flag = taskService.excuteProduceRule(produceInputMap,outputMaterial,outputNum,taskId);
            if (!flag) {
                logger.error("任务执行异常,请联系管理员,检查库存情况");
                quartzManager.removeJob(jobName,jobGroupName,triggerName,triggerGroupName);
                taskService.updateTaskStatus(taskId, Constant.ERROR);
            }
        }
    }
}
