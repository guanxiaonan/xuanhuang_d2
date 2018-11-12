package com.dmx.material.controller;

import com.dmx.material.Constant.Constant;
import com.dmx.material.pojo.InventoryMonitor;
import com.dmx.material.pojo.Result;
import com.dmx.material.service.InventoryMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Description:库存监控Controller
 * @Date: Create at 11:18, 2017/12/20
 * @Author: Matthew
 */
@RestController
public class InventoryMonitorController {
    @Autowired
    private InventoryMonitorService inventoryMonitorService;

    /**
     * post方式请求：request header Content-Type的值必须是：application/x-www-form-urlencoded
     * @param inventoryMonitor
     * @return
     */
    @PostMapping(value = "/inventory-monitor")
    public Result add(InventoryMonitor inventoryMonitor) {
        Result result = new Result();
        try {
            inventoryMonitorService.add(inventoryMonitor);
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
     * @param inventoryMonitor
     * @return
     */
    @PutMapping(value = "/inventory-monitor")
    public Result update(InventoryMonitor inventoryMonitor) {
        Result result = new Result();
        if (inventoryMonitor.getId() == null) {
            result.setCode(Constant.ID_NOT_NULL_CODE);
            result.setMessage(Constant.ID_NOT_NULL_MESSAGE);
            return result;
        }

        try {
            inventoryMonitorService.update(inventoryMonitor);
            result.setCode(Constant.SUCCESS_CODE);
            result.setMessage(Constant.SUCCESS_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.FAIL_CODE);
            result.setMessage(Constant.FAIL_MESSAGE);

        }
        return result;
    }

    @GetMapping(value = "/inventory-monitor/{id}")
    public Result findById(@PathVariable Long id) {
        Result result = new Result();
        InventoryMonitor inventoryMonitor = null;
        try {
            inventoryMonitor = inventoryMonitorService.findById(id);
            if (inventoryMonitor == null) {
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        result.setData(inventoryMonitor);
        return result;
    }

    @DeleteMapping(value = "/inventory-monitor/{id}")
    public Result deleteById(@PathVariable Long id) {
        Result result = new Result();
        //先查
        InventoryMonitor inventoryMonitor = inventoryMonitorService.findById(id);
        try {
            inventoryMonitorService.delete(inventoryMonitor.getId());
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

    @GetMapping(value = "/inventory-monitor")
    public Result findList() {
        Result result = new Result();
        List<InventoryMonitor> list = null;
        try {
            list = inventoryMonitorService.findList();
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
}