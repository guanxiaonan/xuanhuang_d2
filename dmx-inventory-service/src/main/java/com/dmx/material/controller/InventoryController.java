package com.dmx.material.controller;

import com.dmx.material.Constant.Constant;
import com.dmx.material.pojo.Inventory;
import com.dmx.material.pojo.Result;
import com.dmx.material.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Date: Create at 16:28, 2017/12/18
 * @Author: Matthew
 */
@RestController
@RefreshScope
public class InventoryController {

    //test config server
    @Value("${test}")
    private String test;
    @GetMapping(value = "/config")
    public String testConfig() {
        return test;
    }


    @Autowired
    private InventoryService inventoryService;

    /**
     * post方式请求：request header Content-Type的值必须是：application/x-www-form-urlencoded
     * @param inventory
     * @return
     */
    @PostMapping(value = "/inventory")
    public Result add(Inventory inventory) {
        Result result = new Result();
        Date date = new Date();
        try {
            inventoryService.add(inventory);
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
     * @param inventory
     * @return
     */
    @PutMapping(value = "/inventory")
    public Result update(Inventory inventory) {
        Result result = new Result();
        if (inventory.getId() == null) {
            result.setCode(Constant.ID_NOT_NULL_CODE);
            result.setMessage(Constant.ID_NOT_NULL_MESSAGE);
            return result;
        }

        try {
            inventoryService.update(inventory);
            result.setCode(Constant.SUCCESS_CODE);
            result.setMessage(Constant.SUCCESS_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.FAIL_CODE);
            result.setMessage(Constant.FAIL_MESSAGE);

        }
        return result;
    }

    @GetMapping(value = "/inventory/{id}")
    public Result findById(@PathVariable Long id) {
        Result result = new Result();
        Inventory inventory = null;
        try {
            inventory = inventoryService.findById(id);
            if (inventory == null) {
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        result.setData(inventory);
        return result;
    }

    @DeleteMapping(value = "/inventory/{id}")
    public Result deleteById(@PathVariable Long id) {
        Result result = new Result();
        //先查
        Inventory inventory = inventoryService.findById(id);
        try {
            inventoryService.delete(inventory.getId());
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

    @GetMapping(value = "/inventory")
    public Result findList() {
        Result result = new Result();
        List<Inventory> list = null;
        try {
            list = inventoryService.findList();
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
