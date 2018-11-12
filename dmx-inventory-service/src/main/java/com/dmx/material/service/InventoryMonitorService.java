package com.dmx.material.service;

import com.dmx.material.pojo.InventoryMonitor;

import java.util.List;

/**
 * @Description: 物料进出库记录Service
 * @Date: Create at 11:06, 2017/12/20
 * @Author: Matthew
 */
public interface InventoryMonitorService {
    public void add(InventoryMonitor inventoryMonitor);

    public void update(InventoryMonitor inventoryMonitor);

    public void delete(Long id);

    public InventoryMonitor findById(Long id);

    public List<InventoryMonitor> findList();
}
