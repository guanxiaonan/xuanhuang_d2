package com.dmx.material.mapper;

import com.dmx.material.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description:
 * @Date: Create at 14:51, 2017/12/18
 * @Author: Matthew
 */
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user(name,post_id,birthday,phone,gender,email,flag,create_time," +
            "update_time,last_login_time,login_count,detail) VALUES(#{name},#{postId}," +
            "#{birthday},#{phone},#{gender},#{email},#{flag},#{createTime},#{updateTime}," +
            "#{lastLoginTime},#{loginCount},#{detail})")
    public void add(User user);

    @Update("UPDATE user SET name=#{name},post_id=#{postId},birthday=#{birthday},phone=#{phone}," +
            "create_time=#{createTime},phone=#{phone},detail=#{detail},gender=#{gender}," +
            "update_time=#{updateTime},last_login_time=#{lastLoginTime},login_count=#{loginCount}" +
            " WHERE id=#{id}")
    public void update(User user);

    @Delete("DELETE FROM user WHERE id=#{id}")
    public void delete(Long id);

    @Select("SELECT * FROM user WHERE id=#{id}")
    @Results({
            @Result(column = "post_id", property = "postId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "last_login_time", property = "lastLoginTime"),
            @Result(column = "login_count", property = "loginCount"),
            @Result(column = "update_time", property = "updateTime")
    })
    public User findById(Long id);

    @Select("SELECT * FROM user")
    @Results({
            @Result(column = "post_id", property = "postId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "last_login_time", property = "lastLoginTime"),
            @Result(column = "login_count", property = "loginCount"),
            @Result(column = "update_time", property = "updateTime")
    })
    public List<User> findList();

    @Select("select name FROM user WHERE id=#{id}")
    public String findNameById(Long id);


    @Select("select post_name FROM user_post WHERE id=#{postId}")
    String getPostNameByPostId(Long postId);

    @Select("select * from user WHERE name=#{0} and pwd=#{1}")
    @Results({
            @Result(column = "post_id", property = "postId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "last_login_time", property = "lastLoginTime"),
            @Result(column = "login_count", property = "loginCount"),
            @Result(column = "update_time", property = "updateTime")
    })
    User findUserWithNameAndPwd(String name, String pwd);
}
