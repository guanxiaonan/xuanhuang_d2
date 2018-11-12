package com.dmx.material.service;

import com.dmx.material.pojo.InventoryRecord;

import java.util.List;

/**
 * @Description: 物料进出库记录Service
 * @Date: Create at 11:06, 2017/12/20
 * @Author: Matthew
 */
public interface InventoryRecordService {
    public void add(InventoryRecord inventoryRecord);

    public void update(InventoryRecord inventoryRecord);

    public void delete(Long id);

    public InventoryRecord findById(Long id);

    public List<InventoryRecord> findList();

    List<InventoryRecord> findItemByPage(Integer currentPageNo, Integer pageSize);

    int getTotal();
}
