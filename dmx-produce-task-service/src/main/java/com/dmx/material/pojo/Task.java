package com.dmx.material.pojo;

import java.util.Date;

/**
 * @Description: 物料库存实体类
 * @Date: Create at 16:11, 2017/12/13
 * @Author: Matthew
 */
public class Task {

    private Long id;    // 任务ID
    private String produceId;     // 生产计划id, 如3件物料A,2件物料B 能生成 1件物料C 对应的id
    private String name;    // 任务名称
    private String group;   // 任务分组
    private Integer status;     // 任务状态 0禁用 1启
    private Integer checkStatus;    // 审核状态 0 已创建 1 审核通过 2 审核驳回
    private String cron;    // 任务运行时间表达式
    private String quartzClass;     // 定时任务处理类
    private Date createTime;
    private Long creatorId;
    private String desc;
    private String email;
    private String triggerName;
    private String triggerGroupName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduceId() {
        return produceId;
    }

    public void setProduceId(String produceId) {
        this.produceId = produceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getQuartzClass() {
        return quartzClass;
    }

    public void setQuartzClass(String quartzClass) {
        this.quartzClass = quartzClass;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroupName() {
        return triggerGroupName;
    }

    public void setTriggerGroupName(String triggerGroupName) {
        this.triggerGroupName = triggerGroupName;
    }
}
