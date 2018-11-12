package com.dmx.one.service;

import com.dmx.one.pojo.Result;
import com.dmx.one.pojo.User;
import com.dmx.one.service.impl.UserFeignServiceHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Date: Create at 10:01, 2017/12/15
 * @Author: Matthew
 */
@FeignClient(value = "dmx-user-service",fallback = UserFeignServiceHystrix.class)
public interface UserFeignService {

    @RequestMapping(value = "/user/name/{id}",method = RequestMethod.GET)
    public String findNameById(@RequestHeader(value = "id") Long id);

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test();

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    Result findList();

    @RequestMapping(value = "/user/page/{currentPageNo}/{pageSize}",method = RequestMethod.GET)
    public Result findByPage(@RequestHeader(value = "currentPageNo") int currentPageNo,
                             @RequestHeader(value = "pageSize")int pageSize);

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    Result findById(@RequestHeader(value = "id") Long id);

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    Result add(@RequestBody User user);

    @RequestMapping(value = "/user/post",method = RequestMethod.GET)
    Result findPostList();

    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    Result update(@RequestBody User user);

    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    Result delete(@RequestHeader(value = "id") Long id);

    @RequestMapping(value = "/user/total",method = RequestMethod.GET)
    Result getTotal();

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    Result login(@RequestBody User user);

    @RequestMapping(value = "/user/logout",method = RequestMethod.GET)
    Result logout();
}
