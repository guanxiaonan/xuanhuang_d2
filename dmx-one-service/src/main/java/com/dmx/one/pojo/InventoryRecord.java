package com.dmx.one.pojo;

import java.util.Date;

/**
 * @Description: 物料进出库记录实体
 * @Date: Create at 20:16, 2018/02/20
 * @Author: Matthew
 */
public class InventoryRecord {
    /**
     * 进出库记录id
     */
    private Long id;

    /**
     * 记录的物料id
     */
    private Long materialId;

    /**
     * 物料进出库数量
     */
    private Long number;

    /**
     * 1：入库;  2：出库;
     */
    private Integer type;

    /**
     * 操作员id(用户id)
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建详情
     */
    private String createDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateDesc() {
        return createDesc;
    }

    public void setCreateDesc(String createDesc) {
        this.createDesc = createDesc;
    }
}
