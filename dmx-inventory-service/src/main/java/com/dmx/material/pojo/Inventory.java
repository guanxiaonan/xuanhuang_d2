package com.dmx.material.pojo;

/**
 * @Description: 物料库存实体类
 * @Date: Create at 16:11, 2017/12/13
 * @Author: Matthew
 */
public class Inventory {

    /**
     * 物料库存表id
     */
    private Long id;

    /**
     * 物料id
     */
    private Long materialId;

    /**
     * 物料库存数(单位:件)
     */
    private Long number;

    /**
     * 该物料库存状态：0-不可用;1-可用
     */
    private Integer flag;

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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
