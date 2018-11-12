package com.dmx.one.mapper;

import com.dmx.one.pojo.Monitor;
import com.dmx.one.pojo.MonitorParam;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface MonitorMapper {

    @Insert("insert into call_monitor(service_name,call_method,call_time,call_user) values (#{serviceName},#{callMethod},#{callTime},#{callUser})")
    void addCallRecord(MonitorParam monitor);

    @Select("select serviceName,count(*) as count from call_monitor group by service_name")
    @Results({
            @Result(column = "service_name", property = "serviceName"),
            @Result(column = "count", property = "callCount")
    })
    List<Monitor> findAll();

    @Select("select service_name,count(*) as count from call_monitor where call_time between #{date1} and #{date2} group by service_name")
    @Results({
            @Result(column = "service_name", property = "serviceName"),
            @Result(column = "count", property = "callCount")
    })
    List<Monitor> findList(@Param(value = "date1") Date date1,@Param(value = "date2") Date date2);
}
