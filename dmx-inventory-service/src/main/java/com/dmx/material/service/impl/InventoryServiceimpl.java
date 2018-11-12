package com.dmx.material.service.impl;

import com.dmx.material.mapper.InventoryMapper;
import com.dmx.material.pojo.Inventory;
import com.dmx.material.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Date: Create at 10:02, 2017/12/15
 * @Author: Matthew
 */
@Service
public class InventoryServiceimpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public void add(Inventory inventory) {
        inventoryMapper.add(inventory);
    }

    @Override
    public void update(Inventory inventory){
        Inventory updateInventory = findById(inventory.getId());

        if (inventory.getMaterialId() != null) {
            updateInventory.setMaterialId(inventory.getMaterialId());
        }
        if (inventory.getNumber() != null) {
            updateInventory.setNumber(inventory.getNumber());
        }
        if (inventory.getFlag() != null) {
            updateInventory.setFlag(inventory.getFlag());
        }
        //获取当前用户
        // todo
        inventoryMapper.update(updateInventory);
    }

    @Override
    public void delete(Long id) {
        inventoryMapper.delete(id);
    }

    @Override
    public Inventory findById(Long id) {
        return inventoryMapper.findById(id);
    }

    @Override
    public List<Inventory> findList() {
        return inventoryMapper.findList();
    }
}
