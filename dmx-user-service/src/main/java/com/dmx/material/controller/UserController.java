package com.dmx.material.controller;

import com.dmx.material.Constant.Constant;
import com.dmx.material.pojo.Result;
import com.dmx.material.pojo.User;
import com.dmx.material.pojo.UserInfo;
import com.dmx.material.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Date: Create at 16:28, 2017/12/18
 * @Author: Matthew
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * post方式请求：request header Content-Type的值必须是：application/x-www-form-urlencoded
     * @param user
     * @return
     */
    @PostMapping(value = "/user")
    public Result add(User user) {
        Result result = new Result();
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        try {
            userService.add(user);
            result.setCode(Constant.SUCCESS_CODE);
            result.setMessage(Constant.SUCCESS_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.FAIL_CODE);
            result.setMessage(Constant.FAIL_MESSAGE);

        }
        return result;
    }

    /**
     *  put方式请求：request header Content-Type的值必须是：application/x-www-form-urlencoded
     * @param user
     * @return
     */
    @PutMapping(value = "/user")
    public Result update(User user) {
        Result result = new Result();
        if (user.getId() == null) {
            result.setCode(Constant.ID_NOT_NULL_CODE);
            result.setMessage(Constant.ID_NOT_NULL_MESSAGE);
            return result;
        }

        try {
            userService.update(user);
            result.setCode(Constant.SUCCESS_CODE);
            result.setMessage(Constant.SUCCESS_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.FAIL_CODE);
            result.setMessage(Constant.FAIL_MESSAGE);

        }
        return result;
    }

    @GetMapping(value = "/user/{id}")
    public Result findById(@PathVariable Long id) {
        Result result = new Result();
        User user = null;
        try {
            user = userService.findById(id);
            if (user == null) {
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        result.setData(user);
        return result;
    }

    @DeleteMapping(value = "/user/{id}")
    public Result deleteById(Long id) {
        Result result = new Result();
        //先查
        User user = userService.findById(id);
        try {
            userService.delete(user.getId());
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

    @GetMapping(value = "/user/v1")
    public Result findList() {
        Result result = new Result();
        List<User> list = null;
        try {
            list = userService.findList();
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

    @GetMapping(value = "/user")
    public Result findAll() {
        Result result = new Result();
        List<UserInfo> list = null;
        try {
            list = userService.findAll();
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


    /**
     * 根据用户id获取用户名
     */
    @RequestMapping(value = "/user/name/{id}",method = RequestMethod.GET)
    public String findNameById(@PathVariable Long id) {
        return userService.findNameById(id);
    }

    /**
     * 用户登录
     * @return
     */
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public Result login(@RequestBody User user) {
        Result result = new Result();
        User user1 = null;
        try {
            user1 = userService.login(user);
            if (user1 == null) {
                result.setCode(Constant.USER_NOT_EXIST_CODE);
                result.setMessage(Constant.USER_NOT_EXIST_MESSAGE);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        result.setData(user1);
        return result;
    }
//    /**
//     * 用户退出登录
//     * @return
//     */
//    @RequestMapping(value = "/user/logout",method = RequestMethod.GET)
//    public Result logout() {
//        Result result = new Result();
//        User user1 = null;
//        try {
//            user1 = userService.logout();
//            if (user1 == null) {
//                result.setCode(Constant.USER_NOT_EXIST_CODE);
//                result.setMessage(Constant.USER_NOT_EXIST_MESSAGE);
//                return result;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        result.setCode(Constant.SUCCESS_CODE);
//        result.setMessage(Constant.SUCCESS_MESSAGE);
//        result.setData(user1);
//        return result;
//    }
}
