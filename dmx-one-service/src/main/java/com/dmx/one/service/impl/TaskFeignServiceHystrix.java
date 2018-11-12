package com.dmx.one.service.impl;

import com.dmx.one.Constant.Constant;
import com.dmx.one.pojo.Result;
import com.dmx.one.pojo.TaskParam;
import com.dmx.one.service.TaskFeignService;
import org.springframework.stereotype.Component;

@Component
public class TaskFeignServiceHystrix implements TaskFeignService {
    @Override
    public Result addTask(TaskParam task) {
        return setReturnMsg();
    }

    public Result test() {
        return setReturnMsg();
    }

    public Result findByPage(int currentPageNo, int pageSize) {
        return setReturnMsg();
    }

    public Result getTotal() {
        return setReturnMsg();
    }

    @Override
    public Result findTaskInfos() {
        return setReturnMsg();
    }

    public Result findList() {
        return setReturnMsg();
    }

    public Result update(TaskParam task) {
        return setReturnMsg();
    }

    public Result findById(Long id) {
        return setReturnMsg();
    }

    public Result delete(Long id) {
        return setReturnMsg();
    }

    public Result pauseProduceTask(Long id) {
        return setReturnMsg();
    }

    public Result resumeProduceTask(Long id) {
        return setReturnMsg();
    }

    private Result setReturnMsg() {
        Result result = new Result();
        result.setCode(Constant.SERVICE_NOT_ENABLE_CODE);
        result.setMessage("Task:"+Constant.SERVICE_NOT_ENABLE_MESSAGE);
        return result;
    }
}
