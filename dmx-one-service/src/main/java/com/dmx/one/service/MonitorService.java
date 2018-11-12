package com.dmx.one.service;

import com.dmx.one.pojo.Monitor;
import com.dmx.one.pojo.MonitorParam;

import java.util.Date;
import java.util.List;

public interface MonitorService {
    /**
     * 获取服务一个时间段内的调用次数
     * @param date1
     * @param date2
     * @return
     */
    List<Monitor> getCallTimes(Date date1, Date date2);

    List<Monitor> getTodayCallTimes();

    List<Monitor> getLastWeekCallTimes();

    void addCallRecord(MonitorParam monitor);

    List<Monitor> findList();
}
