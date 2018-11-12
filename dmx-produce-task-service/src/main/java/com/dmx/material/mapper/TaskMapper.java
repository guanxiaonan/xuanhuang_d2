package com.dmx.material.mapper;

import com.dmx.material.pojo.InventoryRecord;
import com.dmx.material.pojo.ProduceRule;
import com.dmx.material.pojo.Task;
import com.dmx.material.pojo.Trigger;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {
    @Insert("INSERT INTO inventory_produce_task(produce_id,job_name,job_group,job_status,check_status,cron,quartz_class,create_time,creator_id,job_desc,email,trigger_name,trigger_group_name) VALUES(#{produceId},#{name},#{group},#{status},#{checkStatus},#{cron},#{quartzClass},#{createTime},#{creatorId},#{desc},#{email},#{triggerName},#{triggerGroupName})")
    void add(Task task);

    @Update("UPDATE inventory_produce_task SET produce_id=#{produceId}," +
            "job_name=#{name},job_group=#{group},job_status=#{status},check_status=#{checkStatus}," +
            "cron=#{cron},quartz_class=#{quartzClass},job_desc=#{desc},email=#{email} WHERE id=#{id}")
    void update(Task task);

    @Delete("DELETE FROM inventory_produce_task WHERE id=#{id}")
    void delete(Long id);

    @Select("SELECT * FROM inventory_produce_task WHERE id=#{id}")
    @Results({
            @Result(column = "produce_id", property = "produceId"),
            @Result(column = "job_name", property = "name"),
            @Result(column = "job_group", property = "group"),
            @Result(column = "job_status", property = "status"),
            @Result(column = "check_status", property = "checkStatus"),
            @Result(column = "quartz_class", property = "quartzClass"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "job_desc", property = "desc"),
            @Result(column = "trigger_name", property = "triggerName"),
            @Result(column = "trigger_group_name", property = "triggerGroupName")
    })
    Task findById(Long id);

    @Select("SELECT * FROM inventory_produce_task where job_status!=1")
    @Results({
            @Result(column = "produce_id", property = "produceId"),
            @Result(column = "job_name", property = "name"),
            @Result(column = "job_group", property = "group"),
            @Result(column = "job_status", property = "status"),
            @Result(column = "check_status", property = "checkStatus"),
            @Result(column = "quartz_class", property = "quartzClass"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "job_desc", property = "desc"),
            @Result(column = "trigger_name", property = "triggerName"),
            @Result(column = "trigger_group_name", property = "triggerGroupName")
    })
    List<Task> findList();

    @Select("SELECT * FROM inventory_produce_task where job_status=2 and check_status=1")
    @Results({
            @Result(column = "produce_id", property = "produceId"),
            @Result(column = "job_name", property = "name"),
            @Result(column = "job_group", property = "group"),
            @Result(column = "job_status", property = "status"),
            @Result(column = "check_status", property = "checkStatus"),
            @Result(column = "quartz_class", property = "quartzClass"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "job_desc", property = "desc"),
            @Result(column = "trigger_name", property = "triggerName"),
            @Result(column = "trigger_group_name", property = "triggerGroupName")
    })
    List<Task> getNewTask();

//    @Select("SELECT * FROM task_trigger WHERE job_id=#{id}")
//    @Results({
//            @Result(column = "job_id", property = "jobId"),
//            @Result(column = "trigger_name", property = "triggerName"),
//            @Result(column = "trigger_group_name", property = "triggerGroupName"),
//            @Result(column = "status", property = "status")
//    })
//    Trigger getTriggerByJobId(Long id);

    @Select("SELECT * FROM produce_rule WHERE rule_id=#{produceId}")
    @Results({
            @Result(column = "input_material_and_num", property = "inputMaterialAndNum"),
            @Result(column = "output_material", property = "outputMaterial"),
            @Result(column = "output_num", property = "outputNum")
    })
    ProduceRule getProduceRuleByProduceId(String produceId);

    @Select("SELECT id FROM material WHERE name=#{name}")
    Long getMaterialIdByMaterialName(String name);

    @Select("SELECT number FROM inventory WHERE mid=#{mid}")
    Long getInventoryByMid(Long mid);

    @Select("SELECT alarm_num FROM inventory_monitor WHERE mid=#{mid} and alarm_flag=1")
    Long getSecurityInventoryByMid(Long mid);

    @Update("UPDATE inventory SET number=#{number} WHERE mid=#{mid} and flag=1")
    void updateInventoryByMid(@Param(value = "mid") Long mid,@Param(value = "number") Long number);

    @Insert("INSERT INTO inventory_record(mid,number,type,creator_id,create_time,create_desc" +
            ") VALUES(#{materialId},#{number},#{type},#{creatorId},#{createTime},#{createDesc})")
    void addInventoryRecord(InventoryRecord inventoryRecord);

    @Update("UPDATE inventory_produce_task SET job_status=#{status} WHERE id=#{taskId}")
    void updateTaskStatus(@Param(value = "taskId")Long taskId, @Param(value = "status")int status);

    @Select("SELECT * FROM inventory_produce_task where job_status=3 and check_status=1")
    @Results({
            @Result(column = "produce_id", property = "produceId"),
            @Result(column = "job_name", property = "name"),
            @Result(column = "job_group", property = "group"),
            @Result(column = "job_status", property = "status"),
            @Result(column = "check_status", property = "checkStatus"),
            @Result(column = "quartz_class", property = "quartzClass"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "job_desc", property = "desc"),
            @Result(column = "trigger_name", property = "triggerName"),
            @Result(column = "trigger_group_name", property = "triggerGroupName")
    })
    List<Task> getRunningTaskList();

    @Insert("INSERT INTO produce_rule(rule_id,input_material_and_num,output_material,output_num)" +
            " VALUES(#{ruleId},#{inputMaterialAndNum},#{outputMaterial},#{outputNum})")
    void addProduceRule(ProduceRule rule);

    @Select("select email from inventory_produce_task where id = #{taskId}")
    String getEmail(Long taskId);
}
