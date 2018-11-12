package com.dmx.one.controller;

import com.dmx.one.pojo.Material;
import com.dmx.one.pojo.MaterialToJson;
import com.dmx.one.pojo.Result;
import com.dmx.one.service.MaterialFeignService;
import com.dmx.one.service.UserFeignService;
import com.dmx.one.utils.EntityToJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Description:
 * @Date: Create at 16:28, 2017/12/18
 * @Author: Matthew
 */
@RestController
public class MaterialController {

    @Autowired
    private MaterialFeignService materialFeignService;

    @Autowired
    private UserFeignService userFeignService;

    /**
     * 获取物料列表
     * @return
     */
    @RequestMapping(value = "/material",method = RequestMethod.GET)
    public Result findList() {
        Result result = materialFeignService.findList();
        List list = (List) result.getData();

        if (list == null) {
            return result;
        }

        List<MaterialToJson> toJsonList = new ArrayList<>();
        for (int i = 0; i < list.size();i++) {
            LinkedHashMap m = (LinkedHashMap)list.get(i);
            MaterialToJson materialToJson = new MaterialToJson();

            EntityToJsonUtil<MaterialToJson> toJsonUtil = new EntityToJsonUtil<>();

            MaterialToJson toJson = toJsonUtil.toJsonEntity(m, materialToJson);

            if (m.get("creatorId") != null) {
                String name = userFeignService.findNameById(Long.parseLong(m.get("creatorId").toString()));
                toJson.setCreatorName(name);
            }

            if (m.get("modifierId") != null) {
                String name = userFeignService.findNameById(Long.parseLong(m.get("modifierId").toString()));
                toJson.setModifierName(name);
            }
            toJsonList.add(toJson);
        }
        result.setData(toJsonList);
        return result;
    }

    /**
     * 获取土壤数据
     * @return
     */
    @RequestMapping(value = "/soil_get",method = RequestMethod.GET)
    public Result soilGet(){
        return materialFeignService.soilGet();
    }

    /**
     * 获取空气数据
     */
    @RequestMapping(value = "/air_get",method = RequestMethod.GET)
    public Result airGet(){
        return materialFeignService.airGet();
    }

    /**
     * 获取光照数据
     * return
     */
    @RequestMapping(value = "/light_get",method = RequestMethod.GET)
    public Result lightGet(){
        return materialFeignService.lightGet();
    }

    /**
     * 获取物料列表
     * @return
     */
    @RequestMapping(value = "/material/page/{currentPageNo}/{pageSize}",
            method = RequestMethod.GET)
    public Result findByPage(@PathVariable int currentPageNo,
                             @PathVariable int pageSize) {
        Result result = materialFeignService.findByPage(currentPageNo,pageSize);
        List list = (List) result.getData();

        if (list == null) {
            return result;
        }

        List<MaterialToJson> toJsonList = new ArrayList<>();
        for (int i = 0; i < list.size();i++) {
            LinkedHashMap m = (LinkedHashMap)list.get(i);
            MaterialToJson materialToJson = new MaterialToJson();

            EntityToJsonUtil<MaterialToJson> toJsonUtil = new EntityToJsonUtil<>();

            MaterialToJson toJson = toJsonUtil.toJsonEntity(m, materialToJson);

            if (m.get("creatorId") != null) {
                String name = userFeignService.findNameById(Long.parseLong(m.get("creatorId").toString()));
                toJson.setCreatorName(name);
            }

            if (m.get("modifierId") != null) {
                String name = userFeignService.findNameById(Long.parseLong(m.get("modifierId").toString()));
                toJson.setModifierName(name);
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
    @RequestMapping(value = "/material/{id}",method = RequestMethod.GET)
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
    @RequestMapping(value = "/material/type",method = RequestMethod.GET)
    public Result findTypeNameList() {
        return materialFeignService.findTypeNameList();
    }

    /**
     * 获取物料名称和id列表
     * @return
     */
    @RequestMapping(value = "/material/nameList",method = RequestMethod.GET)
    public Result findNameList() {
        return materialFeignService.findNameList();
    }

    /**
     * 增加单个物料信息
     * 默认接收：consumes = "application/json"
     * 必须要有 @RequestBody,不然无法通过feign传到material服务
     * @param material
     * @return
     */
    @RequestMapping(value = "/material",method = RequestMethod.POST)
    public Result add(@RequestBody Material material) {
        return materialFeignService.add(material);
    }

    /**
     * 修改单个物料信息
     * @param material
     * @return
     */
    @RequestMapping(value = "/material",method = RequestMethod.PUT)
    public Result upadte(@RequestBody Material material) {
        Result result =  materialFeignService.update(material);
        return result;
    }

    /**
     * 删除单个物料
     * @param id
     * @return
     */
    @RequestMapping(value = "/material/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable Long id) {
        return materialFeignService.delete(id);
    }


    @GetMapping(value = "/material/totalNum")
    public Result getTotalNum() {
        System.out.println("hello");


        return materialFeignService.getTotal();
    }

    /**
     * test
     * @return
     */
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test() {

       return materialFeignService.test();

    }
}
