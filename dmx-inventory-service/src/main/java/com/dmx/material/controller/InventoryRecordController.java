package com.dmx.material.controller;

import com.dmx.material.Constant.Constant;
import com.dmx.material.Exception.InventoryNotEnoughException;
import com.dmx.material.pojo.InventoryRecord;
import com.dmx.material.pojo.Result;
import com.dmx.material.service.InventoryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * @Description:物料进出库记录Controller
 * @Date: Create at 11:18, 2017/12/20
 * @Author: Matthew
 */
@RestController
//@RequestMapping("/inventory-service")
public class InventoryRecordController {
    @Autowired
    private InventoryRecordService inventoryRecordService;

    /**
     * post方式请求：request header Content-Type的值必须是：application/x-www-form-urlencoded
     * @param inventoryRecord
     * @return
     */
    @RequestMapping(value = "/inventory-record",method = RequestMethod.POST)
    public Result add(@RequestBody InventoryRecord inventoryRecord) {
        Result result = new Result();
        Date date = new Date();
        inventoryRecord.setCreateTime(date);
        inventoryRecord.setCreatorId(1L);
        try {
            inventoryRecordService.add(inventoryRecord);
            result.setCode(Constant.SUCCESS_CODE);
            result.setMessage(Constant.SUCCESS_MESSAGE);

        } catch (InventoryNotEnoughException e) {
            result.setCode(Constant.INV_NOT_ENOUGH_CODE);
            result.setMessage(Constant.INV_NOT_ENOUGH_MSG);

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.FAIL_CODE);
            result.setMessage(Constant.FAIL_MESSAGE);
        }
        return result;
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public Result test() {
        Result result = new Result();
        System.out.println("9002");
        return result;
    }

    /**
     *  put方式请求：request header Content-Type的值必须是：application/x-www-form-urlencoded
     * @param inventoryRecord
     * @return
     */
    @PutMapping(value = "/inventory-record")
    public Result update(InventoryRecord inventoryRecord) {
        Result result = new Result();
        if (inventoryRecord.getId() == null) {
            result.setCode(Constant.ID_NOT_NULL_CODE);
            result.setMessage(Constant.ID_NOT_NULL_MESSAGE);
            return result;
        }

        try {
            inventoryRecordService.update(inventoryRecord);
            result.setCode(Constant.SUCCESS_CODE);
            result.setMessage(Constant.SUCCESS_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.FAIL_CODE);
            result.setMessage(Constant.FAIL_MESSAGE);

        }
        return result;
    }

    @GetMapping(value = "/inventory-record/{id}")
    public Result findById(@PathVariable Long id) {
        Result result = new Result();
        InventoryRecord inventoryRecord = null;
        try {
            inventoryRecord = inventoryRecordService.findById(id);
            if (inventoryRecord == null) {
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        result.setData(inventoryRecord);
        return result;
    }

    @DeleteMapping(value = "/inventory-record/{id}")
    public Result deleteById(@PathVariable Long id) {
        Result result = new Result();
        //先查
        InventoryRecord inventoryRecord = inventoryRecordService.findById(id);
        try {
            inventoryRecordService.delete(inventoryRecord.getId());
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

    @GetMapping(value = "/inventory-record")
    public Result findList() {
        Result result = new Result();
        List<InventoryRecord> list = null;
        try {
            list = inventoryRecordService.findList();
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

    @GetMapping(value = "/inventory-record/page/{currentPageNo}/{pageSize}")
    public Result findInventoryRecordByPage(@PathVariable Integer currentPageNo,
                                     @PathVariable Integer pageSize) {
        Result result = new Result();
        List<InventoryRecord> list = null;
        try {
            list = inventoryRecordService.findItemByPage(currentPageNo,pageSize);
            if (list == null || list.size() == 0) {
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.FAIL_CODE);
            result.setMessage(Constant.FAIL_MESSAGE);
            return result;
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        result.setData(list);
        return result;
    }

    @GetMapping(value = "/inventory-record/totalNum")
    public Result getTotalNum() {
        Result result = new Result();
        int total = 0;
        try {
            total = inventoryRecordService.getTotal();
            if (total == 0) {
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.FAIL_CODE);
            result.setMessage(Constant.FAIL_MESSAGE);
            return result;
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        result.setData(total);
        return result;
    }
}