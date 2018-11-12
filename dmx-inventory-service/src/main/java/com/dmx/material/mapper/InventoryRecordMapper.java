package com.dmx.material.mapper;

import com.dmx.material.pojo.InventoryRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InventoryRecordMapper {
    @Insert("INSERT INTO inventory_record(mid,number,type,creator_id,create_time,create_desc" +
            ") VALUES(#{materialId},#{number},#{type},#{creatorId},#{createTime},#{createDesc})")
    void add(InventoryRecord inventoryRecord);

    @Update("UPDATE inventory_record SET mid=#{materialId},number=#{number},type=#{type}," +
            "creator_id=#{creatorId},create_time=#{createTime},create_desc=#{createDesc}" +
            " WHERE id=#{id}")
    void update(InventoryRecord inventoryRecord);

    @Delete("DELETE FROM inventory_record WHERE id=#{id}")
    void delete(Long id);

    @Select("SELECT * FROM inventory_record WHERE id=#{id}")
    @Results({
            @Result(column = "mid", property = "materialId"),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "create_desc", property = "createDesc")
    })
    InventoryRecord findById(Long id);

    @Select("SELECT r.id,r.mid,r.number,r.type,t.type_name,r.creator_id,r.create_time," +
            "r.create_desc FROM inventory_record r,inventory_type t where r.type = t.id")
    @Results({
            @Result(column = "mid", property = "materialId"),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "type_name", property = "typeName"),
            @Result(column = "create_desc", property = "createDesc"),
    })
    List<InventoryRecord> findList();

    @Select("SELECT count(*) FROM inventory_record")
    int countTotal();
}
