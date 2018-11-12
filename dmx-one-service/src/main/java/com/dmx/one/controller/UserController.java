package com.dmx.one.controller;

import com.dmx.one.pojo.User;
import com.dmx.one.pojo.Result;
import com.dmx.one.service.UserFeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

/**
 * @Description:
 * @Date: Create at 16:28, 2017/12/18
 * @Author: Matthew
 */
@RestController
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserFeignService userFeignService;


    /**
     * 获取用户列表
     * @return
     */
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public Result findList() {
        Result res = userFeignService.findList();
        System.out.println(res.getData());
        return res;
    }

    /**
     * 获取用户列表(有分页)
     * @return
     */
    @RequestMapping(value = "/user/page/{currentPageNo}/{pageSize}",
            method = RequestMethod.GET)
    public Result findByPage(@PathVariable int currentPageNo,
                             @PathVariable int pageSize) {
        return userFeignService.findByPage(currentPageNo,pageSize);
    }

    /**
     * 根据id获取单个用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable Long id) {
        return userFeignService.findById(id);
    }

    /**
     * 获取用户岗位列表
     * @return
     */
    @RequestMapping(value = "/user/post",method = RequestMethod.GET)
    public Result findPostList() {
        return userFeignService.findPostList();
    }

    /**
     * 用户登录
     * @return
     */
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public Result login(@RequestBody User user,HttpSession session) {
        logger.info("user:{}",user);
        Result result = userFeignService.login(user);
        LinkedHashMap map = ((LinkedHashMap)result.getData());
        if (map != null) {
            String name = (String)map.get("name");
            String pwd = (String)map.get("pwd");
            session.setAttribute("user",name+"|"+pwd);
            logger.info("登录成功！name:{},pwd:{}",name,pwd);
        } else {
            logger.info("没有该用户！name:{},pwd:{}",user.getName(),user.getPwd());
        }
        return result;
    }
    /**
     * 用户退出登录
     * @return
     */
    @RequestMapping(value = "/user/logout",method = RequestMethod.GET)
    public Result logout() {
        return userFeignService.logout();
    }

    /**
     * 增加单个用户信息
     * 默认接收：consumes = "application/json"
     * 必须要有 @RequestBody,不然无法通过feign传到user服务
     * @param user
     * @return
     */
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public Result add(@RequestBody User user) {
        return userFeignService.add(user);
    }

    /**
     * 修改单个物料信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public Result upadte(@RequestBody User user) {
        Result result =  userFeignService.update(user);
        return result;
    }

    /**
     * 删除单个物料
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable Long id) {
        return userFeignService.delete(id);
    }


    @GetMapping(value = "/user/totalNum")
    public Result getTotalNum() {
        System.out.println("hello");
        return userFeignService.getTotal();
    }
}
