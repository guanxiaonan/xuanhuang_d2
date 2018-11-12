package com.dmx.material.service.impl;

import com.dmx.material.mapper.UserMapper;
import com.dmx.material.pojo.User;
import com.dmx.material.pojo.UserInfo;
import com.dmx.material.service.UserService;
import com.dmx.material.utils.MD5Utils;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Date: Create at 10:02, 2017/12/15
 * @Author: Matthew
 */
@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public void update(User user){
        User updateUser = findById(user.getId());

        if (user.getName() != null) {
            updateUser.setName(user.getName());
        }
        if (user.getBirthday() != null) {
            updateUser.setBirthday(user.getBirthday());
        }
        if (user.getEmail() != null) {
            updateUser.setEmail(user.getEmail());
        }
        if (user.getDetail() != null) {
            updateUser.setDetail(user.getDetail());
        }
        if (user.getFlag() != null) {
            updateUser.setFlag(user.getFlag());
        }
        if (user.getGender() != null) {
            updateUser.setGender(user.getGender());
        }
        if (user.getPhone() != null) {
            updateUser.setPhone(user.getPhone());
        }
        if (user.getPostId() != null) {
            updateUser.setPostId(user.getPostId());
        }
        //获取当前用户
        // todo
        updateUser.setUpdateTime(new Date());
        userMapper.update(updateUser);
    }

    @Override
    public void delete(Long id) {
        userMapper.delete(id);
    }

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> findList() {
        return userMapper.findList();
    }

    @Override
    public String findNameById(Long id) {
        return userMapper.findNameById(id);
    }

    @Override
    public List<UserInfo> findAll() {
        List<User> list = userMapper.findList();
        List<UserInfo> infoList = new ArrayList<>();
        list.forEach((user) -> {
            UserInfo info = new UserInfo();
            info.setName(user.getName());
            info.setBirthday(user.getBirthday());
            info.setEmail(user.getEmail());
            info.setFlag(user.getFlag());
            info.setCreateTime(user.getCreateTime());
            info.setGender(user.getGender());
            info.setLastLoginTime(user.getLastLoginTime());
            info.setLoginCount(user.getLoginCount());
            info.setUpdateTime(user.getUpdateTime());
            info.setDetail(user.getDetail());
            info.setPhone(user.getPhone());
            Long postId = user.getPostId();
            info.setPostId(postId);
            String postName = userMapper.getPostNameByPostId(postId);
            info.setPostName(postName);
            infoList.add(info);
        });

        return infoList;
    }

    public User login(User user) {
        String name = user.getName();
        String pwd = user.getPwd();
        if (Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(pwd)) {
            return null;
        }
        return userMapper.findUserWithNameAndPwd(name, MD5Utils.MD5Encode(pwd,"utf-8",true));
    }
}
