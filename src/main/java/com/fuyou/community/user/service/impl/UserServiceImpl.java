package com.fuyou.community.user.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.exception.ServiceException;
import com.fuyou.community.sys.model.SysLabelinfo;
import com.fuyou.community.sys.model.dto.DelLabelDto;
import com.fuyou.community.sys.util.CurrentUtil;
import com.fuyou.community.sys.util.PasswordUtil;
import com.fuyou.community.user.dao.UserEduInfoMapper;
import com.fuyou.community.user.dao.UserLabelinfoMapper;
import com.fuyou.community.user.dao.UserMapper;
import com.fuyou.community.user.dao.UserWorkinfoMapper;
import com.fuyou.community.user.model.User;
import com.fuyou.community.user.model.UserEduInfo;
import com.fuyou.community.user.model.UserLabelinfo;
import com.fuyou.community.user.model.UserWorkinfo;
import com.fuyou.community.user.model.dto.BaseInfoDto;
import com.fuyou.community.user.model.dto.LoginDto;
import com.fuyou.community.user.model.vo.LoginVO;
import com.fuyou.community.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;
    private final UserEduInfoMapper userEduInfoMapper;
    private final UserWorkinfoMapper userWorkinfoMapper;
    private final UserLabelinfoMapper userLabelinfoMapper;

    private final RedisTemplate<String, Object> redisTemplate;


    @Override
    public User getUserById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public ResultVo login(LoginDto dto) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, dto.getUserName()));
        String uid = IdUtil.simpleUUID();
        String tokenUid = "token:" + uid;
        if (ObjectUtil.isEmpty(user)) {
            return ResultVo.fail(4000, "用户不存在,请先注册");
        } else {
            String excr = PasswordUtil.excr(dto.getUserPassword(), user.getUserSalt());
            if (!user.getUserPassword().equals(excr)) {
                return ResultVo.fail(5000, "密码错误");
            }
        }
        redisTemplate.opsForValue().set(tokenUid, JSON.toJSONString(user), 30, TimeUnit.MINUTES);
        LoginVO loginVO = new LoginVO();
        loginVO.setUserId(user.getId());
        loginVO.setUserName(user.getUserName());
        loginVO.setUserAlias(user.getUserAlias());
        loginVO.setTokenId(tokenUid);
//        设置工具类中的用户信息
        CurrentUtil.setLoginUser(user);
        return ResultVo.success(2000, "登录成功", loginVO);
    }

    @Override
    public ResultVo signUp(User user) {
        User old = userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getUserName, user.getUserName()));
        if (ObjectUtil.isNotEmpty(old)) {
            return ResultVo.fail(5000, "用户名已存在");
        }
        user.setId(IdUtil.simpleUUID());
//        盐值
        String salt = RandomUtil.randomString(5);
        String excr = PasswordUtil.excr(user.getUserPassword(), salt);
        user.setUserPassword(excr);
        user.setUserSalt(salt);
        int insert = userMapper.insert(user);
        if (insert < 1) {
            return ResultVo.fail(5000, "用户注册失败");
        }
        return ResultVo.success(2000, "注册成功");
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public ResultVo saveEdu(UserEduInfo eduInfo) {
        UserEduInfo oldEduInfo = userEduInfoMapper.selectOne(Wrappers.lambdaQuery(UserEduInfo.class)
                .eq(UserEduInfo::getUserId, eduInfo.getUserId()));
        if (ObjectUtil.isEmpty(oldEduInfo)) {
            eduInfo.setId(IdUtil.simpleUUID());
            userEduInfoMapper.insert(eduInfo);
        }
        {
            userEduInfoMapper.update(eduInfo, Wrappers.lambdaUpdate(UserEduInfo.class)
                    .eq(UserEduInfo::getUserId, eduInfo.getUserId()));
        }
        return ResultVo.success(2000, "更新成功");
    }

    @Override
    public ResultVo saveWork(UserWorkinfo workinfo) {
        UserWorkinfo userWorkinfo = userWorkinfoMapper.selectOne(Wrappers.lambdaQuery(UserWorkinfo.class)
                .eq(UserWorkinfo::getUserId, workinfo.getUserId()));
        if (ObjectUtil.isEmpty(userWorkinfo)) {
            userWorkinfoMapper.insert(workinfo);
        } else {
            userWorkinfoMapper.update(workinfo, Wrappers.lambdaUpdate(UserWorkinfo.class)
                    .eq(UserWorkinfo::getUserId, workinfo.getUserId()));
        }
        return ResultVo.success(2000, "更新成功");
    }

    @Override
    public ResultVo saveLabel(UserLabelinfo labelinfo) {
        int insert = userLabelinfoMapper.insert(labelinfo);
        return insert > 0 ? ResultVo.success(2000, "保存成功") : ResultVo.fail(5000, "保存失败");
    }

    @Override
    public ResultVo updateBaseinfo(BaseInfoDto dto) {
        int update = userMapper.update(null, Wrappers.lambdaUpdate(User.class)
                .eq(User::getId, dto.getId())
                .set(User::getUserName, dto.getUserName())
                .set(User::getUserAlias, dto.getUserAlias())
                .set(User::getUserSex, dto.getUserSex())
                .set(User::getUserSign, dto.getUserSign()));
        return update > 0 ? ResultVo.success(2000, "更新成功") : ResultVo.fail(5000, "更新失败");
    }

    public ResultVo delLabel(DelLabelDto dto){
        userLabelinfoMapper.delete(Wrappers.lambdaQuery(UserLabelinfo.class)
                .eq(UserLabelinfo::getUserId,dto.getUserId())
                .eq(UserLabelinfo::getLabelId,dto.getLabelId()));
        return ResultVo.success(2000,"移除成功");
    }

    @Override
    public List<SysLabelinfo> getUserLabels(String userID) {
        List<SysLabelinfo> userLabelinfos = userLabelinfoMapper.getUserLabels(userID);
        return userLabelinfos;
    }
}
