package com.dmx.one.service;

import com.dmx.one.pojo.InventoryRecord;
import com.dmx.one.pojo.Result;
import com.dmx.one.service.impl.InventoryFeignServiceHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 库存服务
 */
@FeignClient(value = "dmx-inventory-service",fallback = InventoryFeignServiceHystrix.class)
public interface InventoryFeignService {
    @RequestMapping(value = "/inventory-record",method = RequestMethod.POST)
    public Result add(@RequestBody InventoryRecord inventoryRecord);

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    Result test();

    @RequestMapping(value = "/inventory-record/page/{currentPageNo}/{pageSize}",method = RequestMethod.GET)
    public Result findByPage(@RequestHeader(value = "currentPageNo") int currentPageNo,
                             @RequestHeader(value = "pageSize")int pageSize);

    @RequestMapping(value = "/inventory-record/totalNum",method = RequestMethod.GET)
    Result getTotal();

    @RequestMapping(value = "/inventory",method = RequestMethod.GET)
    Result findList();
}
