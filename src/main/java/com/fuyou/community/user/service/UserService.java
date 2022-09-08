package com.fuyou.community.user.service;

import com.fuyou.community.user.model.User;

/**
 * @Author yanfuyou
 * @Description 用户相关业务
 * @Date 下午8:59 2022/9/8
 */
public interface UserService {

    /**
     * @Author yanfuyou
     * @Description
     * @Date 下午9:34 2022/9/8
     * @Param id 用户id
     * @Return user 用户信息
     */
    User getUserById(String id);
}
