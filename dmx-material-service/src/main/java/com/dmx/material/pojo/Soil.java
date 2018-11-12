package com.dmx.material.pojo;

import java.util.Date;

/**
 * @Description: 土壤数据信息实体类
 * @Date: Create at 16:11, 2017/12/13
 * @Author: Matthew
 */
public class Soil {

    private Long soilId;

    private double soilTemperature;

    private double soilHumidity;

    private Date date;

    private Integer teagarId;

    private String teagarName;

    public Long getSoilId() {
        return soilId;
    }

    public void setSoilId(Long soilId) {
        this.soilId = soilId;
    }

    public double getSoilTemperature() {
        return soilTemperature;
    }

    public void setSoilTemperature(double soilTemperature) {
        this.soilTemperature = soilTemperature;
    }

    public double getSoilHumidity() {
        return soilHumidity;
    }

    public void setSoilHumidity(double soilHumidity) {
        this.soilHumidity = soilHumidity;
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
