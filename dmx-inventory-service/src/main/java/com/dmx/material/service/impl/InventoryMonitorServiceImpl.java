package com.dmx.material.service.impl;

import com.dmx.material.mapper.InventoryMonitorMapper;
import com.dmx.material.pojo.InventoryMonitor;
import com.dmx.material.pojo.InventoryMonitor;
import com.dmx.material.service.InventoryMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Date: Create at 11:08, 2017/12/20
 * @Author: Matthew
 */
@Service
public class InventoryMonitorServiceImpl implements InventoryMonitorService {

    @Autowired
    private InventoryMonitorMapper inventoryMonitorMapper;

    @Override
    public void add(InventoryMonitor inventoryMonitor) {
        inventoryMonitorMapper.add(inventoryMonitor);
    }

    @Override
    public void update(InventoryMonitor inventoryMonitor) {

        InventoryMonitor updateInventoryMonitor = findById(inventoryMonitor.getId());

        if (inventoryMonitor.getMaterialId() != null) {
            updateInventoryMonitor.setMaterialId(inventoryMonitor.getMaterialId());
        }
        if (inventoryMonitor.getAlarmFlag() != null) {
            updateInventoryMonitor.setAlarmFlag(inventoryMonitor.getAlarmFlag());
        }
        if (inventoryMonitor.getAlarmMessage() != null) {
            updateInventoryMonitor.setAlarmMessage(inventoryMonitor.getAlarmMessage());
        }
        if (inventoryMonitor.getAlarmNum() != null) {
            updateInventoryMonitor.setAlarmNum(inventoryMonitor.getAlarmNum());
        }
        if (inventoryMonitor.getAlarmUserId() != null) {
            updateInventoryMonitor.setAlarmUserId(inventoryMonitor.getAlarmUserId());
        }
        //获取当前用户
        // todo
        inventoryMonitorMapper.update(updateInventoryMonitor);
    }

    @Override
    public void delete(Long id) {
        inventoryMonitorMapper.delete(id);
    }

    @Override
    public InventoryMonitor findById(Long id) {
        return inventoryMonitorMapper.findById(id);
    }

    @Override
    public List<InventoryMonitor> findList() {
        return inventoryMonitorMapper.findList();
    }
}
