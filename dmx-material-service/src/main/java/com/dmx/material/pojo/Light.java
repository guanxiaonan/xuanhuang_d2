package com.dmx.material.pojo;

import java.util.Date;

/**
 * @Description: 土壤数据信息实体类
 * @Date: Create at 16:11, 2017/12/13
 * @Author: Matthew
 */
public class Light {

    private Long lightId;

    private double lux;

    private Date date;

    private Integer teagarId;

    private String teagarName;

    public Long getLightId() {
        return lightId;
    }

    public void setLightId(Long lightId) {
        this.lightId = lightId;
    }

    public double getLux() {
        return lux;
    }

    public void setLux(double lux) {
        this.lux = lux;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTeagarId() {
        return teagarId;
    }

    public void setTeagarId(Integer teagarId) {
        this.teagarId = teagarId;
    }

    public String getTeagarName() {
        return teagarName;
    }

    public void setTeagarName(String teagarName) {
        this.teagarName = teagarName;
    }
}
