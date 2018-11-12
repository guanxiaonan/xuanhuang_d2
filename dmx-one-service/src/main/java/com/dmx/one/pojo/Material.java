package com.dmx.one.pojo;

import java.util.Date;

/**
 * @Description: 物料基本信息实体类
 * @Date: Create at 16:11, 2017/12/13
 * @Author: Matthew
 */
public class Material {

    private Long id;

    private String name;

    private String code;

    private String barcode;

    private Integer type;

    //private String typeName;

    private String detail;

    private Date createTime;

    private Long creatorId;

    //private String creatorName;

    private Date updateTime;

    private Long modifierId;

    //private String modifierName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

//    public String getTypeName() {
//        return typeName;
//    }
//
//    public void setTypeName(String typeName) {
//        this.typeName = typeName;
//    }
//
//    public String getCreatorName() {
//        return creatorName;
//    }
//
//    public void setCreatorName(String creatorName) {
//        this.creatorName = creatorName;
//    }
//
//    public String getModifierName() {
//        return modifierName;
//    }
//
//    public void setModifierName(String modifierName) {
//        this.modifierName = modifierName;
//    }
}
