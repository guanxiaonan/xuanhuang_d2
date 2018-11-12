package com.dmx.material.pojo;

/**
 * @Description: 物料分类实体
 * @Date: Create at 21:04, 2017/12/21
 * @Author: Matthew
 */
public class MaterialType {
    private Long id;
    private String typeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
