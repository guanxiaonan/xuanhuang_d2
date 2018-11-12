package com.dmx.material.pojo;

import java.util.Date;

/**
 * @Description: 土壤数据信息实体类
 * @Date: Create at 16:11, 2017/12/13
 * @Author: Matthew
 */
public class Air {

    private Long airId;

    private double airTemperature;

    private double airHumidity;

    private Date date;

    private Integer teagarId;

    private String teagarName;

    public Long getAirId() {
        return airId;
    }

    public void setAirId(Long airId) {
        this.airId = airId;
    }

    public double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public double getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(double airHumidity) {
        this.airHumidity = airHumidity;
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
