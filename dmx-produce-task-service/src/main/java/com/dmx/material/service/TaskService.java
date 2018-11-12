package com.dmx.material.service;

import com.dmx.material.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Date: Create at 10:01, 2017/12/15
 * @Author: Matthew
 */
public interface TaskService {

    void add(Task task);

    void addTask(TaskParam task) throws Exception;

    void update(Task task);

    void delete(Task id) throws Exception;

    Task findById(Long id);

    List<Task> findList();

    List<TaskInfo> findAllTaskInfo();

    List<Task> getNewTask();

    //Trigger getTriggerByJobId(Long id);

    ProduceRule getProduceRule(String produceId);

    boolean excuteProduceRule(Map<String, Long> produceInputMap, String outputMaterial, Integer outputNum, Long taskId);

    void updateTaskStatus(Long taskId, int status);

    List<Task> getRunningTaskList();

    void addCronJobWithProduceId(List<Task> newTaskList);

    void pauseProduceTask(Long jobId);

    void resumeProduceTask(Long jobId);
}
