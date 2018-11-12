package com.dmx.material.mapper;

import com.dmx.material.pojo.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Result;

import java.util.List;

/**
 * @Description:
 * @Date: Create at 14:51, 2017/12/18
 * @Author: Matthew
 */
@Mapper
public interface MaterialMapper {
    @Insert("INSERT INTO material(name,type,code,barcode,create_time,creator_id,detail,modifier_id,update_time) VALUES(#{name},#{type},#{code},#{barcode},#{createTime},#{creatorId},#{detail},#{modifierId},#{updateTime})")
    public void add(Material material);

    @Update("UPDATE material SET name=#{name},type=#{type},code=#{code},barcode=#{barcode},create_time=#{createTime},creator_id=#{creatorId},detail=#{detail},modifier_id=#{modifierId},update_time=#{updateTime} WHERE id=#{id}")
    public void update(Material material);

    @Delete("DELETE FROM material WHERE id=#{id}")
    public void delete(Long id);

    @Select("SELECT * FROM material WHERE id=#{id}")
    @Results({
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "modifier_id", property = "modifierId"),
            @Result(column = "update_time", property = "updateTime")
    })
    public Material findById(Long id);

    @Select("SELECT m.id,m.name,m.type,t.type_name,m.code,m.barcode,m.create_time,m.creator_id," +
            "m.detail,m.modifier_id,m.update_time FROM material m,material_type t " +
            "where m.type = t.id")
    @Results({
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "modifier_id", property = "modifierId"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "type_name", property = "typeName")
    })
    public List<Material> findList();



    @Select("select s.soil_id,s.soil_temperature,s.soil_humidity,s.date,s.teagar_id,t.teagar_name " +
            "from soil s,tea_garden t " +
            "where s.soil_id = t.teagar_id")
    @Results({
            @Result(column = "soil_id",property = "soilId"),
            @Result(column = "soil_temperature",property = "soilTemperature"),
            @Result(column = "soil_humidity",property = "soilHumidity"),
            @Result(column = "teagar_id",property = "teagarId"),
            @Result(column = "teagar_name",property = "teagarName")
    })
    public List<Soil> soilGet();

    @Select("SELECT a.air_id,a.air_temperature,a.air_humidity,a.date," +
            "a.teagar_id,t.teagar_name FROM air a,tea_garden t " +
            "WHERE a.teagar_id = t.teagar_id")
    @Results({
            @Result(column = "air_id",property = "airId"),
            @Result(column = "air_temperature",property = "airTemperature"),
            @Result(column = "air_humidity",property = "airHumidity"),
            @Result(column = "teagar_id",property = "teagarId"),
            @Result(column = "teagar_name",property = "teagarName")
    })
    public List<Air> airGet();

    @Select("SELECT l.light_id,l.lux,l.date,l.teagar_id,t.teagar_name " +
            "FROM light l,tea_garden t " +
            "WHERE l.teagar_id = t.teagar_id")
    @Results({
            @Result(column = "light_id",property = "lightId"),
            @Result(column = "teagar_id",property = "teagarId"),
            @Result(column = "teagar_name",property = "teagarName")
    })
    public List<Light> lightGet();

    @Select("SELECT id,type_name FROM material_type")
    @Results({
            @Result(column = "type_name", property = "typeName")
    })
    public List<MaterialType> findTypeNameList();

    @Select("SELECT m.id,m.name,m.type,t.type_name,m.code,m.barcode,m.create_time,m.creator_id," +
            "m.detail,m.modifier_id,m.update_time FROM material m,material_type t " +
            "where m.type = t.id")
    @Results({
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "modifier_id", property = "modifierId"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "type_name", property = "typeName")
    })
    public List<Material> findAll();

    @Select("select count(*) from material")
    int countTotal();

    @Select("select m.id,m.name from material m")
    List<Material> findNameList();

    @Select("select * from material_type where id=#{id}")
    @Results({
            @Result(column = "type_name", property = "typeName")
    })
    public MaterialType findTypeNameById(Long id);
}
