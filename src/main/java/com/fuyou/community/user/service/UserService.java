package com.fuyou.community.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.sys.model.PageDto;
import com.fuyou.community.sys.model.SysLabelinfo;
import com.fuyou.community.sys.model.dto.DelLabelDto;
import com.fuyou.community.user.model.User;
import com.fuyou.community.user.model.UserEduInfo;
import com.fuyou.community.user.model.UserLabelinfo;
import com.fuyou.community.user.model.UserWorkinfo;
import com.fuyou.community.user.model.dto.BaseInfoDto;
import com.fuyou.community.user.model.dto.LoginDto;
import com.fuyou.community.user.model.vo.AvatarVo;

import javax.xml.transform.Result;
import java.util.List;
import java.util.Set;

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
    ResultVo delLabel(DelLabelDto dto);

    /**
     * @Author yanfuyou
     * @Description 获取用户标签
     * @Date 下午10:05 2022/9/26
     */
    List<SysLabelinfo> getUserLabels(String userID);

    /**
     * @Author yanfuyou
     * @Description 获取用户教育信息
     * @Date 下午10:37 2022/10/14
     */
    UserEduInfo getEduInfo(String id);

    /**
     * @Author yanfuyou
     * @Description 获取用户工作信息
     * @Date 下午10:53 2022/10/14
     */
    UserWorkinfo getWorkInfo(String id);

    /**
     * @Author yanfuyou
     * @Description 获取用户头像
     * @Date 下午9:10 2022/10/15
     */
    IPage<AvatarVo> getUserAvatars(PageDto<Set<String>> avatarDto);
}
