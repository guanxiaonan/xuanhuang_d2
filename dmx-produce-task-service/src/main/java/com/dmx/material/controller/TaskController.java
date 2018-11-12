package com.dmx.material.controller;

import com.dmx.material.Constant.Constant;
import com.dmx.material.pojo.Task;
import com.dmx.material.pojo.Result;
import com.dmx.material.pojo.TaskInfo;
import com.dmx.material.pojo.TaskParam;
import com.dmx.material.service.TaskService;
import com.dmx.material.service.impl.TaskServiceimpl;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.processing.SupportedOptions;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Date: Create at 16:28, 2017/12/18
 * @Author: Matthew
 */
@RestController
@RefreshScope
public class TaskController {

    //test config server
    @Value("${test}")
    private String test;

    @Value("${version}")
    private String version;

    @Value("${spring.application.name}")
    private String serviceName;


    @GetMapping(value = "/service")
    public String getServiceNameAndVersion() {
        System.out.println("serviceName:"+serviceName);
        System.out.println("version:"+version);
        return serviceName+":"+version;
    }

    private static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    /**
     * post方式请求：request header Content-Type的值必须是：application/x-www-form-urlencoded
     * @param task
     * @return
     */
//    @PostMapping(value = "/produce-task/v1")
//    public Result add(Task task) {
//        Result result = new Result();
//        if (Strings.isNullOrEmpty(task.getName()) || Strings.isNullOrEmpty(task.getCron())||
//                task.getProduceId() == null) {
//            result.setCode(Constant.PARAM_ERROR_CODE);
//            result.setMessage(Constant.PARAM_ERROR_MESSAGE);
//            result.setData(task);
//            return result;
//        }
//        if (Strings.isNullOrEmpty(task.getGroup())) {
//            task.setGroup(Constant.JOB_GROUP);
//        }
//        task.setStatus(Constant.FORBID);
//        task.setCreateTime(new Date());
//        task.setCheckStatus(1);
//        task.setCreatorId(1L);
//        try {
//            taskService.add(task);
//            result.setCode(Constant.SUCCESS_CODE);
//            result.setMessage(Constant.SUCCESS_MESSAGE);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.setCode(Constant.FAIL_CODE);
//            result.setMessage(Constant.FAIL_MESSAGE);
//        }
//        return result;
//    }
    //TaskParam{createDesc='222', cron='0/20 * * * * ?', email='111', input='原料Bx2+原料Ax2', outputMaterial='成品E', outputNum=1}
    //TaskParam{createDesc='null', cron='null', email='null', input='null', outputMaterial='null', outputNum=null}
    @PostMapping(value = "/produce-task")
    public Result addTask(@RequestBody TaskParam param) {
        logger.info("测试通路：{}",param);
        Result result = new Result();
        if (Strings.isNullOrEmpty(param.getInput()) || Strings.isNullOrEmpty(param.getCron())||
                Strings.isNullOrEmpty(param.getOutputMaterial()) || param.getOutputNum() == null||
        param.getOutputNum() <= 0) {
            result.setCode(Constant.PARAM_ERROR_CODE);
            result.setMessage(Constant.PARAM_ERROR_MESSAGE);
            result.setData(param);
            return result;
        }
        try {
            taskService.addTask(param);
            result.setCode(Constant.SUCCESS_CODE);
            result.setMessage(Constant.SUCCESS_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加生产任务失败！！！回滚操作");
            result.setCode(Constant.FAIL_CODE);
            result.setMessage(Constant.FAIL_MESSAGE);
        }
        return result;
    }

    /**
     *  put方式请求：request header Content-Type的值必须是：application/x-www-form-urlencoded
     * @param task
     * @return
     */
    @PutMapping(value = "/produce-task")
    public Result update(Task task) {
        Result result = new Result();
        if (task.getId() == null) {
            result.setCode(Constant.ID_NOT_NULL_CODE);
            result.setMessage(Constant.ID_NOT_NULL_MESSAGE);
            return result;
        }

        try {
            taskService.update(task);
            result.setCode(Constant.SUCCESS_CODE);
            result.setMessage(Constant.SUCCESS_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.FAIL_CODE);
            result.setMessage(Constant.FAIL_MESSAGE);

        }
        return result;
    }

    @GetMapping(value = "/produce-task/{id}")
    public Result findById(@PathVariable Long id) {
        Result result = new Result();
        Task task = null;
        try {
            task = taskService.findById(id);
            if (task == null) {
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        result.setData(task);
        return result;
    }

    @DeleteMapping(value = "/produce-task/{id}")
    public Result deleteById(@PathVariable Long id) {
        Result result = new Result();

        try {
            //先查
            Task task = taskService.findById(id);
            if (task == null) {
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
            task.setStatus(Constant.FORBID);
            taskService.delete(task);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.FAIL_CODE);
            result.setMessage(Constant.FAIL_MESSAGE);
            return result;
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        return result;
    }

    @GetMapping(value = "/produce-task/v1")
    public Result findList() {
        Result result = new Result();
        List<Task> list = null;
        try {
            list = taskService.findList();
            if (list == null || list.size() == 0) {
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        result.setData(list);
        return result;
    }

    @GetMapping(value = "/produce-task")
    public Result findTaskInfos() {
        Result result = new Result();
        List<TaskInfo> list = null;
        try {
            list = taskService.findAllTaskInfo();
            if (list == null || list.size() == 0) {
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        result.setData(list);
        return result;
    }

    @GetMapping(value = "/produce-task/pause-job/{id}")
    public Result pauseJob(@PathVariable Long id) {
        Result result = new Result();
        try {
            taskService.pauseProduceTask(id);
            result.setCode(Constant.SUCCESS_CODE);
            result.setMessage(Constant.SUCCESS_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.FAIL_CODE);
            result.setMessage(Constant.FAIL_MESSAGE);
        }
        result.setData("jobId="+id);
        return result;
    }

    @GetMapping(value = "/produce-task/resume-job/{id}")
    public Result resumeJob(@PathVariable Long id) {
        Result result = new Result();
        try {
            taskService.resumeProduceTask(id);
            result.setCode(Constant.SUCCESS_CODE);
            result.setMessage(Constant.SUCCESS_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.FAIL_CODE);
            result.setMessage(Constant.FAIL_MESSAGE);
        }
        result.setData("jobId="+id);
        return result;
    }

}
