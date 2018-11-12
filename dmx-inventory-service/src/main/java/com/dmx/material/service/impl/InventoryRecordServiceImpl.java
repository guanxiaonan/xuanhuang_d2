package com.dmx.material.service.impl;

import com.dmx.material.Exception.InventoryNotEnoughException;
import com.dmx.material.mapper.InventoryMapper;
import com.dmx.material.mapper.InventoryRecordMapper;
import com.dmx.material.pojo.Inventory;
import com.dmx.material.pojo.InventoryRecord;
import com.dmx.material.pojo.PageBean;
import com.dmx.material.service.InventoryRecordService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Date: Create at 11:08, 2017/12/20
 * @Author: Matthew
 */
@Service
public class InventoryRecordServiceImpl implements InventoryRecordService {

    private static Logger logger = LoggerFactory.getLogger(InventoryRecordServiceImpl.class);

    @Autowired
    private InventoryRecordMapper inventoryRecordMapper;

    @Autowired
    private InventoryMapper inventoryMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(InventoryRecord inventoryRecord) {
        inventoryRecordMapper.add(inventoryRecord);
        long type = inventoryRecord.getType();
        int sign = (type == 1) ? 1 : -1;
        long num = inventoryRecord.getNumber() * sign;
        if (sign == -1) {
            final Inventory inv = inventoryMapper.getInventoryByMaterialId(inventoryRecord.getMaterialId());
            if (num > inv.getNumber()) {
                logger.error("库存不足！！");
                throw new InventoryNotEnoughException();
            }
        }

        Inventory inventory =inventoryMapper.getInventoryByMaterialId(inventoryRecord.getMaterialId());

        if (inventory == null) {
            Inventory inventory1 = new Inventory();
            inventory1.setMaterialId(inventoryRecord.getMaterialId());
            inventory1.setFlag(1);
            inventory1.setNumber(num);
            inventoryMapper.add(inventory1);
        } else {
            long number = inventory.getNumber();
            long total = number + num;
            inventory.setNumber(total);
            inventoryMapper.update(inventory);
        }
    }

    @Override
    public void update(InventoryRecord inventoryRecord) {

        InventoryRecord updateInventoryRecord = findById(inventoryRecord.getId());

        if (inventoryRecord.getMaterialId() != null) {
            updateInventoryRecord.setMaterialId(inventoryRecord.getMaterialId());
        }
        if (inventoryRecord.getNumber() != null) {
            updateInventoryRecord.setNumber(inventoryRecord.getNumber());
        }
        if (inventoryRecord.getType() != null) {
            updateInventoryRecord.setType(inventoryRecord.getType());
        }
        if (inventoryRecord.getCreatorId() != null) {
            updateInventoryRecord.setCreatorId(inventoryRecord.getCreatorId());
        }
        if (inventoryRecord.getCreateTime() != null) {
            updateInventoryRecord.setCreateTime(inventoryRecord.getCreateTime());
        }
        if (inventoryRecord.getCreateDesc() != null) {
            updateInventoryRecord.setCreateDesc(inventoryRecord.getCreateDesc());
        }
        //获取当前用户
        // todo
        inventoryRecordMapper.update(updateInventoryRecord);
    }

    @Override
    public void delete(Long id) {
        inventoryRecordMapper.delete(id);
    }

    @Override
    public InventoryRecord findById(Long id) {
        return inventoryRecordMapper.findById(id);
    }

    @Override
    public List<InventoryRecord> findList() {
        return inventoryRecordMapper.findList();
    }

    @Override
    public List<InventoryRecord> findItemByPage(Integer currentPageNo, Integer pageSize) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPageNo, pageSize);

        List<InventoryRecord> allItems = inventoryRecordMapper.findList();        //全部
        int countNums = allItems.size();                //总记录数
        PageBean<InventoryRecord> pageData = new PageBean<>(currentPageNo, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData.getItems();
    }

    @Override
    public int getTotal() {
        return inventoryRecordMapper.countTotal();
    }
}
