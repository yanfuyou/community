package com.fuyou.community.user.service.impl;

import com.fuyou.community.user.dao.UserMapper;
import com.fuyou.community.user.model.User;
import com.fuyou.community.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(String id) {
        return userMapper.selectById(id);
    }
}
