package com.dmx.material.pojo;

/**
 * @Description: 物料监控实体
 * @Date: Create at 22:24, 2017/12/19
 * @Author: Matthew
 */
public class InventoryMonitor {
    /**
     * 物料监控id
     */
    private Long id;

    /**
     * 监控的物料id
     */
    private Long materialId;

    /**
     * 库存报警标志值,1:报警;0:不报警
     */
    private Long alarmFlag;

    /**
     * 库存报警值
     */
    private Long alarmNum;

    /**
     * 报警信息
     */
    private String alarmMessage;

    /**
     * 监管员
     */
    private Long alarmUserId;

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

    public Long getAlarmFlag() {
        return alarmFlag;
    }

    public void setAlarmFlag(Long alarmFlag) {
        this.alarmFlag = alarmFlag;
    }

    public Long getAlarmNum() {
        return alarmNum;
    }

    public void setAlarmNum(Long alarmNum) {
        this.alarmNum = alarmNum;
    }

    public String getAlarmMessage() {
        return alarmMessage;
    }

    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage = alarmMessage;
    }

    public Long getAlarmUserId() {
        return alarmUserId;
    }

    public void setAlarmUserId(Long alarmUserId) {
        this.alarmUserId = alarmUserId;
    }
}
