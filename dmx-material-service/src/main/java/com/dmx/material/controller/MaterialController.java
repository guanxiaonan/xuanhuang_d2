package com.dmx.material.controller;

import com.dmx.material.Constant.Constant;
import com.dmx.material.pojo.*;
import com.dmx.material.service.MaterialService;
import com.dmx.material.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private MaterialService materialService;

    /**
     * post方式请求：request header Content-Type的值必须是：application/x-www-form-urlencoded
     * 添加一个物料
     * @param material
     * @return
     */
    @RequestMapping(value = "/material",method = RequestMethod.POST)
    public Result add(@RequestBody Material material) {
        Result result = new Result();
        Date date = new Date();
        material.setCreatorId(1L);
        material.setModifierId(1L);
        material.setCreateTime(date);
        material.setUpdateTime(date);
        material.setCode(IDUtils.getMaterialCode());
        material.setBarcode("barcode111");
        try {
            materialService.add(material);
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
     * put方式请求：request header Content-Type的值必须是：application/x-www-form-urlencoded
     * @param material
     * @return
     */
    @PutMapping(value = "/material")
    public Result update(@RequestBody Material material) {
        Result result = new Result();
        if (material.getId() == null) {
            result.setCode(Constant.ID_NOT_NULL_CODE);
            result.setMessage(Constant.ID_NOT_NULL_MESSAGE);
            return result;
        }

        try {
            materialService.update(material);
            result.setCode(Constant.SUCCESS_CODE);
            result.setMessage(Constant.SUCCESS_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.FAIL_CODE);
            result.setMessage(Constant.FAIL_MESSAGE);

        }
        return result;
    }

    @GetMapping(value = "/material/{id}")
    public Result findById(@PathVariable Long id) {
        Result result = new Result();
        Material material = null;
        try {
            material = materialService.findById(id);
            if (material == null) {
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        result.setData(material);
        return result;
    }

    @DeleteMapping(value = "/material/{id}")
    public Result deleteById(@PathVariable Long id) {
        Result result = new Result();
        //先查
        Material material = materialService.findById(id);
        if (material == null) {
            result.setCode(Constant.RES_NOT_EXIST_CODE);
            result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
            return result;
        }
        try {
            materialService.delete(material.getId());
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

    @GetMapping(value = "/material")
    public Result findList() {
        Result result = new Result();
        List<Material> list = null;
        try {
            list = materialService.findList();
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

    @RequestMapping(value = "/air_get",method = RequestMethod.GET)
    public Result airGet(){
        Result result = new Result();
        List<Air> list = null;
        try{
            list = materialService.airGet();
            if(list == null || list.size() == 0){
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        result.setData(list);
        return result;
    }

    @RequestMapping(value = "/soilGet",method = RequestMethod.GET)
    public Result soilGet(){
        Result result = new Result();
        List<Soil> list = null;
        try{
            list = materialService.soilGet();
            if(list == null || list.size() == 0){
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        result.setData(list);
        return result;
    }

    @GetMapping(value = "/light_get")
    public Result lightGet(){
        Result result = new Result();
        List<Light> list = null;
        try{
            list = materialService.lightGet();
            if(list == null || list.size() == 0){
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        result.setData(list);
        return result;
    }

    @RequestMapping(value = "/material/type",method = RequestMethod.GET)
    public Result findTypeNameList() {
        Result result = new Result();
        List<MaterialType> list = null;
        try {
            list = materialService.findTypeNameList();
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

    @RequestMapping(value = "/material/nameList",method = RequestMethod.GET)
    public Result findMaterialNameList() {
        Result result = new Result();
        List<Material> list = null;
        try {
            list = materialService.findNameList();
            if (list == null || list.size() == 0) {
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
            result.setCode(Constant.SUCCESS_CODE);
            result.setMessage(Constant.SUCCESS_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.FAIL_CODE);
            result.setMessage(Constant.FAIL_MESSAGE);
        }
        result.setData(list);
        return result;
    }

    @GetMapping(value = "/material/page/{currentPageNo}/{pageSize}")
    public Result findMaterialByPage(@PathVariable Integer currentPageNo,
                                     @PathVariable Integer pageSize) {
        Result result = new Result();
        List<Material> list = null;
        try {
            list = materialService.findItemByPage(currentPageNo,pageSize);
            if (list == null || list.size() == 0) {
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.SUCCESS_CODE);
            result.setMessage(Constant.SUCCESS_MESSAGE);
            return result;
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        result.setData(list);
        return result;
    }

    @GetMapping(value = "/material/totalNum")
    public Result getTotalNum() {
        Result result = new Result();
        int total = 0;
        try {
            total = materialService.getTotal();
            if (total == 0) {
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.SUCCESS_CODE);
            result.setMessage(Constant.SUCCESS_MESSAGE);
            return result;
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        result.setData(total);
        return result;
    }

    @GetMapping(value = "/material/type/{id}")
    public Result findTypeNameById(@PathVariable Long id) {
        Result result = new Result();
        MaterialType materialType = null;
        try {
            materialType = materialService.findTypeNameById(id);
            if (materialType == null) {
                result.setCode(Constant.RES_NOT_EXIST_CODE);
                result.setMessage(Constant.RES_NOT_EXIST_MESSAGE);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Constant.SUCCESS_CODE);
            result.setMessage(Constant.SUCCESS_MESSAGE);
            return result;
        }
        result.setCode(Constant.SUCCESS_CODE);
        result.setMessage(Constant.SUCCESS_MESSAGE);
        System.out.println("materialType:"+materialType.getTypeName());
        result.setData(materialType);
        return result;
    }
}
