package com.fuyou.community.user.service;

import com.fuyou.community.common.ResultVo;
import com.fuyou.community.user.model.User;
import com.fuyou.community.user.model.UserEduInfo;
import com.fuyou.community.user.model.UserLabelinfo;
import com.fuyou.community.user.model.UserWorkinfo;
import com.fuyou.community.user.model.dto.BaseInfoDto;
import com.fuyou.community.user.model.dto.LoginDto;

import javax.xml.transform.Result;
import java.util.List;

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
     */
    ResultVo login(LoginDto dto);

    /**
     * @Author yanfuyou
     * @Description 用户注册
     * @Date 下午8:57 2022/9/16
     * @Param 用户信息
     */
    ResultVo signUp(User user);

    /**
     * @Author yanfuyou
     * @Description id更新用户信息
     * @Date 下午10:37 2022/9/16
     */
    int updateUserById(User user);

    /**
     * @Author yanfuyou
     * @Description 保存用户教育信息
     * @Date 下午9:42 2022/9/26
     */
    ResultVo saveEdu(UserEduInfo eduInfo);

    /**
     * @Author yanfuyou
     * @Description 保存用户工作信息
     * @Date 下午9:46 2022/9/26
     */
    ResultVo saveWork(UserWorkinfo workinfo);

    /**
     * @Author yanfuyou
     * @Description 保存用户标签信息
     * @Date 下午9:57 2022/9/26
     */
    ResultVo saveLabel(UserLabelinfo labelinfo);

    /**
     * @Author yanfuyou
     * @Description 更新基本信息
     * @Date 下午9:46 2022/9/26
     */
    ResultVo updateBaseinfo(BaseInfoDto dto);

    /**
     * @Author yanfuyou
     * @Description 移除用户标签
     * @Date 下午10:02 2022/9/26
     */
    ResultVo delLabel(String id);

    /**
     * @Author yanfuyou
     * @Description 获取用户标签
     * @Date 下午10:05 2022/9/26
     */
    List<UserLabelinfo> getUserLabels(String userID);
}
