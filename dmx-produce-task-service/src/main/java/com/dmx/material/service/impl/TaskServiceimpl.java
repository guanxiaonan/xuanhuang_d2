package com.dmx.material.service.impl;

import com.dmx.material.Constant.Constant;
import com.dmx.material.mapper.TaskMapper;
import com.dmx.material.pojo.*;
import com.dmx.material.service.TaskService;
import com.dmx.material.utils.QuartzManager;
import com.dmx.material.utils.TaskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.mail.internet.MimeMessage;
import java.util.*;

/**
 * @Description:
 * @Date: Create at 10:02, 2017/12/15
 * @Author: Matthew
 */
@Service
public class TaskServiceimpl implements TaskService {

    private static Logger logger = LoggerFactory.getLogger(TaskServiceimpl.class);

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    QuartzManager quartzManager;

    @Autowired
    JavaMailSender mailSender;

    public void add(Task task) {
        taskMapper.add(task);
    }
    private Task buildTask(TaskParam param) {
        Task task = new Task();
        task.setName(TaskUtil.getjobName());
        task.setGroup(TaskUtil.getjobGroupName());
        task.setTriggerName(TaskUtil.getTriggerName());
        task.setTriggerGroupName(TaskUtil.getTriggerGroupName());
        task.setStatus(Constant.NEW);
        task.setCreatorId(1L);
        task.setCheckStatus(1);
        task.setCreateTime(new Date());
        task.setEmail(param.getEmail());
        task.setCron(param.getCron());
        task.setDesc(param.getCreateDesc());
        task.setQuartzClass("com.dmx.material.service.impl.ProduceTaskJob");
        return task;
    }

    @Transactional(rollbackFor = Exception.class)
    public void addTask(TaskParam param) throws Exception{
        Task task = buildTask(param);
        String produceId = TaskUtil.getProduceRuleUUID();
        task.setProduceId(produceId);

        ProduceRule rule = new ProduceRule();
        rule.setRuleId(produceId);
        String input = param.getInput();
        String[] items = input.split("\\+");
        String inRes = "";
        int i = 0;
        for (String item : items) {
            String[] arr = item.split("x");
            String name = arr[0];
            String num = arr[1];
            Long mid = taskMapper.getMaterialIdByMaterialName(name);
            if (mid == null) {
                logger.error("不存在该物料：{}",name);
                throw new RuntimeException();
            }
            inRes = inRes + name + ":" + num;
            if (i < items.length - 1) {
                inRes = inRes + ",";
            }
            i++;
        }
        rule.setInputMaterialAndNum(inRes);
        String outName = param.getOutputMaterial();
        Long mid = taskMapper.getMaterialIdByMaterialName(outName);
        if (mid == null) {
            logger.error("不存在该物料：{}",outName);
            throw new RuntimeException();
        }
        rule.setOutputMaterial(outName);

        if (!(param.getOutputNum() > 0)) {
            logger.error("输入物料数量应该大于0：{}",param.getOutputNum());
            throw new RuntimeException();
        }
        rule.setOutputNum(param.getOutputNum());
        taskMapper.addProduceRule(rule);
        taskMapper.add(task);
    }

    public void update(Task task){
        //获取当前用户
        // todo
        taskMapper.update(task);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Task task) throws Exception{
        quartzManager.removeJob(task.getName(),task.getGroup(),task.getTriggerName(),task.getTriggerGroupName());
        taskMapper.update(task);
    }

    public Task findById(Long id) {
        return taskMapper.findById(id);
    }

    public List<Task> findList() {
        return taskMapper.findList();
    }

    public List<TaskInfo> findAllTaskInfo() {
        List<Task> list = taskMapper.findList();
        ArrayList<TaskInfo> taskInfos = new ArrayList<>();
        list.forEach((task) -> {
            TaskInfo taskInfo = new TaskInfo();
            taskInfo.setCron(task.getCron());
            taskInfo.setCreateTime(task.getCreateTime());
            taskInfo.setDesc(task.getDesc());
            taskInfo.setCreatorId(task.getCreatorId());
            taskInfo.setEmail(task.getEmail());
            taskInfo.setJobId(task.getId());
            taskInfo.setStatus(task.getStatus());

            String produceId = task.getProduceId();
            ProduceRule rule = taskMapper.getProduceRuleByProduceId(produceId);
            if (rule != null) {
                String produceRule = rule.getInputMaterialAndNum() + " -> " +rule.getOutputMaterial() + "x" + rule.getOutputNum();
                taskInfo.setRule(produceRule);
            }
            taskInfos.add(taskInfo);
        });
        return taskInfos;
    }

    public List<Task> getNewTask() {
        return taskMapper.getNewTask();
    }

//    public Trigger getTriggerByJobId(Long id) {
//        return taskMapper.getTriggerByJobId(id);
//    }

    public ProduceRule getProduceRule(String produceId) {
        return taskMapper.getProduceRuleByProduceId(produceId);
    }

    @Transactional
    public boolean excuteProduceRule(Map<String, Long> produceInputMap, String outputMaterial, Integer outputNum, Long taskId) {
        boolean flag;
        try {

            flag = true;
            Map<Long, Long> resMap = new HashMap<>();
            Map<Long, Long> consumeMap = new HashMap<>();
            // 一项生产任务中的物料集合
            Set<String> keySet = produceInputMap.keySet();
            for (String name : keySet) {
                // 物料id
                Long mid = taskMapper.getMaterialIdByMaterialName(name);
                // 库存数量
                Long inventoryNum = taskMapper.getInventoryByMid(mid);
                // 安全库存
                Long securityInventory = taskMapper.getSecurityInventoryByMid(mid);
                if (securityInventory == null) {
                    securityInventory = 0L;     // 默认最小为0
                }
                // 生产后剩下的库存数量
                Long res = inventoryNum - produceInputMap.get(name);
                if (res < 0) {
                    flag = false;
                    logger.warn("库存不足警告！！！物料id:{},物料名称:{},库存:{},生产所需:{}",
                            mid, name, inventoryNum, produceInputMap.get(name));
                    logger.warn("停止该生产任务！！！");
                    String email = taskMapper.getEmail(taskId);
                    String msg = "**** 库存不足报警！！！****\n"
                            + "生产计划\n"
                            + "输入:"+produceInputMap+"\n"
                            + "输出:{"+outputMaterial+"="+outputNum+"}\n"
                            + "_______________________\n"
                            + "taskId:" + taskId + "\n"
                            + "物料id:" + mid + "\n"
                            + "物料名称:" + name + "\n"
                            + "库存:" + inventoryNum + "\n"
                            + "生产所需:" + produceInputMap.get(name);
                    sendEmail(email,msg);
                } else {
                    if (res <= securityInventory) {
                        logger.warn("到达安全库存报警!! 物料id:{},物料名称:{},库存:{},生产所需:{},安全库存:{}",
                                mid, name, inventoryNum, produceInputMap.get(name), securityInventory);
                    }
                    resMap.put(mid, res);
                    consumeMap.put(mid, produceInputMap.get(name));
                }
            }

            // 检查通过,开始生产
            if (flag) {
                logger.info("通过检查,开始生产,物料消耗情况[name:number]:{}", produceInputMap);
                Long outputId = taskMapper.getMaterialIdByMaterialName(outputMaterial);
                // 更新成品库存数量
                Long inventoryNum = taskMapper.getInventoryByMid(outputId);
                Long res = inventoryNum + outputNum;
                taskMapper.updateInventoryByMid(outputId, res);
                InventoryRecord inventoryRecord = new InventoryRecord();
                inventoryRecord.setMaterialId(outputId);
                //todo
                inventoryRecord.setCreatorId(1L);
                inventoryRecord.setCreateDesc("定时任务生产,产出");
                inventoryRecord.setCreateTime(new Date());
                inventoryRecord.setType((long) 1);//类型:增加
                inventoryRecord.setNumber((long) outputNum);//增加数量
                taskMapper.addInventoryRecord(inventoryRecord);

                resMap.forEach((k, v) -> {
                    taskMapper.updateInventoryByMid(k, v);
                    InventoryRecord inventoryRecord2 = new InventoryRecord();
                    inventoryRecord2.setMaterialId(k);
                    //todo
                    inventoryRecord2.setCreatorId(1L);
                    inventoryRecord2.setCreateDesc("定时任务生产");
                    inventoryRecord2.setCreateTime(new Date());
                    inventoryRecord2.setType((long) 2);
                    inventoryRecord2.setNumber(consumeMap.get(k));//消耗数量
                    taskMapper.addInventoryRecord(inventoryRecord2);
                });
                logger.info("物料消耗剩余情况[mid:number]:{}", resMap);
                logger.info("产出物料:{},数量:{}", outputMaterial, outputNum);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("执行定时任务失败...,回滚操作");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            flag = false;
        }
        return flag;
    }

    private void sendEmail(String toEmail, String text) {
        try {
            final MimeMessage mimeMessage = mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom("15924162290@163.com");
            message.setTo(toEmail);
            message.setSubject("库存报警");
            message.setText(text);
            mailSender.send(mimeMessage);
            logger.info("发送邮件成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发送邮件失败！");

        }
    }

    @Override
    public void updateTaskStatus(Long taskId, int status) {
        taskMapper.updateTaskStatus(taskId,status);
    }

    @Override
    public List<Task> getRunningTaskList() {
        return taskMapper.getRunningTaskList();
    }

    @Transactional
    public void addCronJobWithProduceId(List<Task> newTaskList) {
        Long taskId = null;
        String name = null;
        String group = null;
        String triggerName = null;
        String triggerGroupName = null;
        try {
            for (Task task : newTaskList) {
                taskId = task.getId();
                String cron = task.getCron();
                name = task.getName();
                group = task.getGroup();
                String produceId = task.getProduceId();
                Long id = task.getId();
                String quartzClass = task.getQuartzClass();
                Class<?> aClass = null;
                try {
                    aClass = Class.forName(quartzClass);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                triggerName = task.getTriggerName();
                triggerGroupName = task.getTriggerGroupName();
                quartzManager.addCronJobWithProduceId(aClass,
                        cron,
                        name,
                        group,
                        triggerName,
                        triggerGroupName,
                        produceId,
                        taskId);
                int status = task.getStatus();
                if (Constant.RUNNING != status) {
                    updateTaskStatus(taskId, Constant.RUNNING);
                }
                logger.info("定时任务添加成功! task:{}  is running",taskId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("执行生产任务失败,回滚操作并将任务状态设为 ERROR,taskId:{}",taskId);
            quartzManager.removeJob(name,group,triggerName,triggerGroupName);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            updateTaskStatus(taskId, Constant.ERROR);
        }
    }
    // 发生 Exception 异常回滚
    //@Transactional(rollbackFor = Exception.class)
    //默认spring事务只在发生未被捕获的 RuntimeException 时才回滚
    @Transactional
    public void pauseProduceTask(Long jobId) {
        try {
            Task task = taskMapper.findById(jobId);
            String jobName = task.getName();
            String jobGroupName = task.getGroup();
            quartzManager.pauseJob(jobName,jobGroupName);
            task.setStatus(Constant.PAUSED);
            taskMapper.update(task);
            logger.info("暂停生产任务成功！jobId:{}",jobId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("暂停生产任务失败！！jobId：{}",jobId);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

    }

    @Transactional
    public void resumeProduceTask(Long jobId) {
        try {
            Task task = taskMapper.findById(jobId);
            String jobName = task.getName();
            String jobGroupName = task.getGroup();
            quartzManager.resumeJob(jobName,jobGroupName);
            task.setStatus(Constant.RUNNING);
            taskMapper.update(task);
            logger.info("恢复暂停的生产任务成功！jobId:{}",jobId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("恢复暂停的生产任务失败！！jobId：{}",jobId);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}
