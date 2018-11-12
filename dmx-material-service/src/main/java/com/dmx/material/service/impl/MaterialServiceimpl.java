package com.dmx.material.service.impl;

import com.dmx.material.mapper.MaterialMapper;
import com.dmx.material.pojo.*;
import com.dmx.material.service.MaterialService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Date: Create at 10:02, 2017/12/15
 * @Author: Matthew
 */
@Service
public class MaterialServiceimpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public void add(Material material) {
        materialMapper.add(material);
    }

    @Override
    public void update(Material material){
        Material updateMaterial = findById(material.getId());

        if (material.getName() != null) {
            updateMaterial.setName(material.getName());
        }
        if (material.getBarcode() != null) {
            updateMaterial.setBarcode(material.getBarcode());
        }
        if (material.getCode() != null) {
            updateMaterial.setCode(material.getCode());
        }
        if (material.getDetail() != null) {
            updateMaterial.setDetail(material.getDetail());
        }
        if (material.getType() != null) {
            updateMaterial.setType(material.getType());
        }
        //获取当前用户
        // todo
        updateMaterial.setModifierId(2L);
        updateMaterial.setUpdateTime(new Date());
        materialMapper.update(updateMaterial);
    }

    @Override
    public void delete(Long id) {
        materialMapper.delete(id);
    }

    @Override
    public Material findById(Long id) {
        return materialMapper.findById(id);
    }

    @Override
    public List<Material> findList() {
        return materialMapper.findList();
    }

    @Override
    public List<Soil> soilGet(){
        return materialMapper.soilGet();
    }

    @Override
    public List<Air> airGet(){
        return materialMapper.airGet();
    }

    @Override
    public List<Light> lightGet(){
        return materialMapper.lightGet();
    }

    @Override
    public List<MaterialType> findTypeNameList() {
        return materialMapper.findTypeNameList();
    }

    @Override
    public List<Material> findNameList() {
        return materialMapper.findNameList();
    }

    @Override
    public List<Material> findItemByPage(int currentPageNo, int pageSize) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPageNo, pageSize);

        List<Material> allItems = materialMapper.findAll();        //全部
        int countNums = allItems.size();                //总记录数
        PageBean<Material> pageData = new PageBean<>(currentPageNo, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData.getItems();
    }

    @Override
    public int getTotal() {
        return materialMapper.countTotal();
    }

    @Override
    public MaterialType findTypeNameById(Long id) {
        return materialMapper.findTypeNameById(id);
    }

}
