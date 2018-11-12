package com.dmx.material.service;

import com.dmx.material.pojo.Result;
import com.dmx.material.pojo.User;
import com.dmx.material.pojo.UserInfo;

import java.util.List;

/**
 * @Description:
 * @Date: Create at 10:01, 2017/12/15
 * @Author: Matthew
 */
public interface UserService {

    public void add(User user);

    public void update(User user);

    public void delete(Long id);

    public User findById(Long id);

    public List<User> findList();

    public String findNameById(Long id);

    List<UserInfo> findAll();

    User login(User user);
}
