package com.dmx.one.service.impl;

import com.dmx.one.Constant.Constant;
import com.dmx.one.pojo.InventoryRecord;
import com.dmx.one.pojo.Result;
import com.dmx.one.service.InventoryFeignService;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Date: Create at 21:19, 2018/02/20
 * @Author: Matthew
 */
@Component
public class InventoryFeignServiceHystrix implements InventoryFeignService{

    @Override
    public Result add(InventoryRecord inventoryRecord) {
        Result result = new Result();
        result.setCode(Constant.SERVICE_NOT_ENABLE_CODE);
        result.setMessage("Inventory:"+Constant.SERVICE_NOT_ENABLE_MESSAGE);
        return result;
    }

    @Override
    public Result test() {
        Result result = new Result();
        result.setCode(9002);
        result.setMessage("Inventory:test");
        return result;
    }

    @Override
    public Result findByPage(int currentPageNo, int pageSize) {
        Result result = new Result();
        result.setCode(Constant.SERVICE_NOT_ENABLE_CODE);
        result.setMessage("Inventory:"+Constant.SERVICE_NOT_ENABLE_MESSAGE);
        return result;
    }

    @Override
    public Result getTotal() {
        Result result = new Result();
        result.setCode(Constant.SERVICE_NOT_ENABLE_CODE);
        result.setMessage("Inventory:"+Constant.SERVICE_NOT_ENABLE_MESSAGE);
        return result;
    }

    @Override
    public Result findList() {
        Result result = new Result();
        result.setCode(Constant.SERVICE_NOT_ENABLE_CODE);
        result.setMessage("Inventory:"+Constant.SERVICE_NOT_ENABLE_MESSAGE);
        return result;
    }
}
