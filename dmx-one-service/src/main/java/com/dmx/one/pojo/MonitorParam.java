package com.dmx.one.pojo;

import java.util.Date;

/**
 * 监控实体
 */
public class MonitorParam {
    private Long id;
    private String serviceName;
    private String callMethod;
    private String callUser;
    private Date callTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCallMethod() {
        return callMethod;
    }

    public void setCallMethod(String callMethod) {
        this.callMethod = callMethod;
    }

    public String getCallUser() {
        return callUser;
    }

    public void setCallUser(String callUser) {
        this.callUser = callUser;
    }

    public Date getCallTime() {
        return callTime;
    }

    public void setCallTime(Date callTime) {
        this.callTime = callTime;
    }
}
