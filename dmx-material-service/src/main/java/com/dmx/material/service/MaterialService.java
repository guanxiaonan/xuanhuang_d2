package com.dmx.material.service;

import com.dmx.material.pojo.*;

import java.util.List;

/**
 * @Description:
 * @Date: Create at 10:01, 2017/12/15
 * @Author: Matthew
 */
public interface MaterialService {

    void add(Material material);

    void update(Material material);

    void delete(Long id);

    Material findById(Long id);

    List<Soil> soilGet();

    List<Air> airGet();

    List<Light> lightGet();

    List<Material> findList();

    List<MaterialType> findTypeNameList();

    List<Material> findItemByPage(int currentPageNo,int pageSize);

    int getTotal();

    List<Material> findNameList();

    MaterialType findTypeNameById(Long id);
}
