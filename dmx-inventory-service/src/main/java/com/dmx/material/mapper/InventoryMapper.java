package com.dmx.material.mapper;

import com.dmx.material.pojo.Inventory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InventoryMapper {
    @Insert("INSERT INTO inventory(mid,number,flag) VALUES(#{materialId},#{number},#{flag})")
    void add(Inventory inventory);

    @Update("UPDATE inventory SET mid=#{materialId},number=#{number},flag=#{flag} WHERE id=#{id}")
    void update(Inventory inventory);

    @Delete("DELETE FROM inventory WHERE id=#{id}")
    void delete(Long id);

    @Select("SELECT * FROM inventory WHERE id=#{id}")
    @Results({
            @Result(column = "mid", property = "materialId")
    })
    Inventory findById(Long id);

    @Select("SELECT * FROM inventory")
    @Results({
            @Result(column = "mid", property = "materialId")
    })
    List<Inventory> findList();

    @Select("SELECT * FROM inventory where mid=#{materialId}")
    @Results({
            @Result(column = "mid", property = "materialId")
    })
    Inventory getInventoryByMaterialId(Long materialId);
}
