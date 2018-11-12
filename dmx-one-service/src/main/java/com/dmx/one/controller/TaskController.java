package com.dmx.one.controller;

import com.dmx.one.pojo.Result;
import com.dmx.one.pojo.TaskParam;
import com.dmx.one.service.TaskFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Date: Create at 16:28, 2017/12/18
 * @Author: Matthew
 */
@RestController
public class TaskController {

    @GetMapping(value = "/config")
    public String testConfig() {
        System.out.println("test");
        return "test";
    }


    @Autowired
    private TaskFeignService taskFeignService;

    /**
     * post方式请求：request header Content-Type的值必须是：application/x-www-form-urlencoded
     * @param task
     * @return
     */
    //@PostMapping(value = "/produce-task")
    @RequestMapping(value = "/produce-task",method = RequestMethod.POST)
    public Result addTask(@RequestBody TaskParam task) {
        System.out.println(task);
        return taskFeignService.addTask(task);
    }

    /**
     *  put方式请求：request header Content-Type的值必须是：application/x-www-form-urlencoded
     * @param task
     * @return
     */
    @PutMapping(value = "/produce-task")
    public Result update(TaskParam task) {
        return taskFeignService.update(task);
    }

    @GetMapping(value = "/produce-task/{id}")
    public Result findById(@PathVariable Long id) {
        return taskFeignService.findById(id);
    }

    @DeleteMapping(value = "/produce-task/{id}")
    public Result deleteById(@PathVariable Long id) {
        System.out.println("delete:"+id);
        return taskFeignService.delete(id);
    }

    @GetMapping(value = "/produce-task/v1")
    public Result findList() {
        return taskFeignService.findList();
    }

    @GetMapping(value = "/produce-task")
    public Result findTaskInfos() {
        return taskFeignService.findTaskInfos();
    }

    @GetMapping(value = "/produce-task/pause-job/{id}")
    public Result pauseJob(@PathVariable Long id) {
        return taskFeignService.pauseProduceTask(id);
    }

    @GetMapping(value = "/produce-task/resume-job/{id}")
    public Result resumeJob(@PathVariable Long id) {
        return taskFeignService.resumeProduceTask(id);
    }

}
