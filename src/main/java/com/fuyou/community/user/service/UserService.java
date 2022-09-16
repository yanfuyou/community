package com.fuyou.community.user.service;

import com.fuyou.community.common.ResultVo;
import com.fuyou.community.user.model.User;
import com.fuyou.community.user.model.dto.LoginDto;

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

    /**
     * @Author yanfuyou
     * @Description 用户登录
     * @Date 下午10:23 2022/9/16
     * @Param 登录信息
     * @Return
     */
    ResultVo login(LoginDto dto);

    /**
     * @Author yanfuyou
     * @Description 用户注册
     * @Date 下午8:57 2022/9/16
     * @Param 用户信息
     * @Return
     */
    ResultVo signUp(User user);

    /**
     * @Author yanfuyou
     * @Description id更新用户信息
     * @Date 下午10:37 2022/9/16
     * @Return
     */
    int updateUserById(User user);
}
