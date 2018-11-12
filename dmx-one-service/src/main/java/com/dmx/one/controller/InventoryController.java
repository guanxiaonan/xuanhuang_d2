package com.dmx.one.controller;

import com.dmx.one.adapter.InterceptorConfig;
import com.dmx.one.pojo.*;
import com.dmx.one.service.InventoryFeignService;
import com.dmx.one.service.MaterialFeignService;
import com.dmx.one.service.UserFeignService;
import com.dmx.one.utils.EntityToJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * @Description:
 * @Date: Create at 16:28, 2017/12/18
 * @Author: Matthew
 */
@RestController
public class InventoryController {
    private static Logger logger = LoggerFactory.getLogger(InventoryController.class);


    @Autowired
    private InventoryFeignService inventoryFeignService;

    @Autowired
    private MaterialFeignService materialFeignService;

    @Autowired
    private UserFeignService userFeignService;

    @RequestMapping(value = "/inventory",method = RequestMethod.GET)
    public Result findList() {
        Result result = inventoryFeignService.findList();
        List list = (List) result.getData();

        if (list == null) {
            return result;
        }

        List<InventoryToJson> toJsonList = new ArrayList<>();
        for (int i = 0; i < list.size();i++) {
            LinkedHashMap m = (LinkedHashMap)list.get(i);
            InventoryToJson inventoryToJson = new InventoryToJson();

            EntityToJsonUtil<InventoryToJson> toJsonUtil = new EntityToJsonUtil<>();

            InventoryToJson toJson = toJsonUtil.toJsonEntity(m, inventoryToJson);

            if (m.get("materialId") != null) {
                Result mRes = materialFeignService.findById(Long.parseLong(m.get("materialId").toString()));
                if (mRes == null) {
                    logger.error("库存中存在物料表中没有的脏数据,请联系管理员 mid:{}",m.get("materialId"));
                } else {
                    toJson.setMaterialName((String) ((LinkedHashMap)mRes.getData()).get("name"));
                }
            }
            toJsonList.add(toJson);
        }
        result.setData(toJsonList);
        return result;
    }


    /**
     * 获取物料列表
     * @return
     */
    @RequestMapping(value = "/inventory-record/page/{currentPageNo}/{pageSize}",
            method = RequestMethod.GET)
    public Result findByPage(@PathVariable int currentPageNo,
                             @PathVariable int pageSize) {
        Result result = inventoryFeignService.findByPage(currentPageNo,pageSize);
        List list = (List) result.getData();

        if (list == null) {
            return result;
        }

        List<InventoryRecordToJson> toJsonList = new ArrayList<>();
        for (int i = 0; i < list.size();i++) {
            LinkedHashMap m = (LinkedHashMap)list.get(i);
            InventoryRecordToJson materialToJson = new InventoryRecordToJson();

            EntityToJsonUtil<InventoryRecordToJson> toJsonUtil = new EntityToJsonUtil<>();

            InventoryRecordToJson toJson = toJsonUtil.toJsonEntity(m, materialToJson);

            if (m.get("creatorId") != null) {
                String name = userFeignService.findNameById(Long.parseLong(m.get("creatorId").toString()));
                toJson.setCreatorName(name);
            }

//            if (m.get("type") != null) {
//                Number obj = (Number)m.get("type");
//                Long typeId = obj.longValue();
//                Result mType = materialFeignService.findNameById(typeId);
//                //Result mType = materialFeignService.findNameById(Long.parseLong(m.get("type").toString()));
//                //String name = (String)mType.getData();
//                LinkedHashMap linkedHashMap = (LinkedHashMap)mType.getData();
//                Object typeName = linkedHashMap.get("typeName");
//                String s = typeName.toString();
//                toJson.setTypeName(s);
//            }

            if (m.get("materialId") != null) {
                Result mRes = materialFeignService.findById(Long.parseLong(m.get("materialId").toString()));
                if (mRes.getData() != null) {
                    toJson.setMaterialName((String) ((LinkedHashMap)mRes.getData()).get("name"));
                }
            }




            toJsonList.add(toJson);
        }
        result.setData(toJsonList);
        return result;
    }

    /**
     * 根据id获取单个物料信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/inventory-record/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable Long id) {
        Result result = materialFeignService.findById(id);
        //需要做类型转换
        LinkedHashMap map = (LinkedHashMap) result.getData();
        MaterialToJson materialToJson = new MaterialToJson();
        EntityToJsonUtil<MaterialToJson> toJsonUtil = new EntityToJsonUtil<>();

        MaterialToJson toJson = toJsonUtil.toJsonEntity(map, materialToJson);
        if (map.get("creatorId") != null) {
            String name = userFeignService.findNameById(Long.parseLong(map.get("creatorId").toString()));
            toJson.setCreatorName(name);
        }

        if (map.get("modifierId") != null) {
            String name = userFeignService.findNameById(Long.parseLong(map.get("modifierId").toString()));
            toJson.setModifierName(name);
        }
        if (toJson != null){
            result.setData(toJson);
        }

        return result;
    }

    /**
     * 获取物料分类信息
     * @return
     */
    @RequestMapping(value = "/inventory-record/type",method = RequestMethod.GET)
    public Result findTypeNameList() {
        return materialFeignService.findTypeNameList();
    }

    /**
     * 增加单个物料信息
     * 默认接收：consumes = "application/json"
     * 必须要有 @RequestBody,不然无法通过feign传到material服务
     * @param inventoryRecord
     * @return
     */
    @RequestMapping(value = "/inventory-record",method = RequestMethod.POST)
    public Result add(@RequestBody InventoryRecord inventoryRecord) {
        return inventoryFeignService.add(inventoryRecord);
    }

    /**
     * 修改单个物料信息
     * @param material
     * @return
     */
    @RequestMapping(value = "/inventory-record",method = RequestMethod.PUT)
    public Result upadte(@RequestBody Material material) {
        Result result =  materialFeignService.update(material);
        return result;
    }

    /**
     * 删除单个物料
     * @param id
     * @return
     */
    @RequestMapping(value = "/inventory-record/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable Long id) {
        return materialFeignService.delete(id);
    }


    @GetMapping(value = "/inventory-record/totalNum")
    public Result getTotalNum() {
        return inventoryFeignService.getTotal();
    }

    /**
     * test
     * @return
     */
    @RequestMapping(value = "/retest",method = RequestMethod.GET)
    public Result test() {
       return inventoryFeignService.test();

    }

}
