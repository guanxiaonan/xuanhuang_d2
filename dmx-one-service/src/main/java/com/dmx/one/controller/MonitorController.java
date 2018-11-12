package com.dmx.one.controller;

import com.dmx.one.Constant.Constant;
import com.dmx.one.pojo.Material;
import com.dmx.one.pojo.MaterialToJson;
import com.dmx.one.pojo.Monitor;
import com.dmx.one.pojo.Result;
import com.dmx.one.service.MaterialFeignService;
import com.dmx.one.service.MonitorService;
import com.dmx.one.service.UserFeignService;
import com.dmx.one.utils.EntityToJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Description:
 * @Date: Create at 16:28, 2017/12/18
 * @Author: Matthew
 */
@RestController
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    /**
     * 获取物料列表
     * @return
     */
    @RequestMapping(value = "/monitor",method = RequestMethod.GET)
    public Result findList() {
        Result result = new Result();
        List<Monitor> list = null;
        try {
            list = monitorService.getTodayCallTimes();
            System.out.println(list);
            result.setCode(Constant.SUCCESS_CODE);
            result.setMessage(Constant.SUCCESS_MESSAGE);
            result.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.FAIL_CODE);
            result.setMessage(Constant.FAIL_MESSAGE);
        }
        return result;
    }
}
