package com.dmx.one.service;

import com.dmx.one.pojo.Result;
import com.dmx.one.pojo.TaskParam;
import com.dmx.one.service.impl.TaskFeignServiceHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 库存服务
 */
@FeignClient(value = "dmx-produce-task-service",fallback = TaskFeignServiceHystrix.class)
public interface TaskFeignService {
    @RequestMapping(value = "/produce-task",method = RequestMethod.POST)
    public Result addTask(@RequestBody TaskParam task);

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    Result test();

    @RequestMapping(value = "/produce-task/page/{currentPageNo}/{pageSize}",method = RequestMethod.GET)
    public Result findByPage(@RequestHeader(value = "currentPageNo") int currentPageNo,
                             @RequestHeader(value = "pageSize") int pageSize);

    @RequestMapping(value = "/produce-task/totalNum",method = RequestMethod.GET)
    Result getTotal();

    @RequestMapping(value = "/produce-task",method = RequestMethod.GET)
    Result findTaskInfos();

    @RequestMapping(value = "/produce-task/v1",method = RequestMethod.GET)
    Result findList();

    @RequestMapping(value = "/produce-task",method = RequestMethod.PUT)
    Result update(TaskParam task);

    @RequestMapping(value = "/produce-task/{id}",method = RequestMethod.GET)
    Result findById(@RequestHeader(value = "id") Long id);

    @RequestMapping(value = "/produce-task/{id}",method = RequestMethod.DELETE)
    Result delete(@RequestHeader(value = "id") Long id);

    @RequestMapping(value = "/produce-task/pause-job/{id}",method = RequestMethod.GET)
    Result pauseProduceTask(@RequestHeader(value = "id")Long id);

    @RequestMapping(value = "/produce-task/resume-job/{id}",method = RequestMethod.GET)
    Result resumeProduceTask(@RequestHeader(value = "id")Long id);
}
