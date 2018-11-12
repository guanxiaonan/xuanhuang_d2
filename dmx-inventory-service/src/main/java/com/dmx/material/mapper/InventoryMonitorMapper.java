package com.dmx.material.mapper;

import com.dmx.material.pojo.InventoryMonitor;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InventoryMonitorMapper {
    @Insert("INSERT INTO inventory_monitor(mid,alarm_flag,alarm_num,alarm_message,alarm_user_id" +
            ") VALUES(#{materialId},#{alarmFlag},#{alarmNum},#{alarmMessage},#{alarmUserId})")
    void add(InventoryMonitor inventoryMonitor);

    @Update("UPDATE inventory_monitor SET mid=#{materialId},alarm_flag=#{alarmFlag},alarm_num=#{alarmNum}," +
            "alarm_message=#{alarmMessage},alarm_user_id=#{alarmUserId} WHERE id=#{id}")
    void update(InventoryMonitor inventoryMonitor);

    @Delete("DELETE FROM inventory_monitor WHERE id=#{id}")
    void delete(Long id);

    @Select("SELECT * FROM inventory_monitor WHERE id=#{id}")
    @Results({
            @Result(column = "mid", property = "materialId"),
            @Result(column = "alarm_flag", property = "alarmFlag"),
            @Result(column = "alarm_num", property = "alarmNum"),
            @Result(column = "alarm_message", property = "alarmMessage"),
            @Result(column = "alarm_user_id", property = "alarmUserId")
    })
    InventoryMonitor findById(Long id);

    @Select("SELECT * FROM inventory_monitor")
    @Results({
            @Result(column = "mid", property = "materialId"),
            @Result(column = "alarm_flag", property = "alarmFlag"),
            @Result(column = "alarm_num", property = "alarmNum"),
            @Result(column = "alarm_message", property = "alarmMessage"),
            @Result(column = "alarm_user_id", property = "alarmUserId")
    })
    List<InventoryMonitor> findList();
}
