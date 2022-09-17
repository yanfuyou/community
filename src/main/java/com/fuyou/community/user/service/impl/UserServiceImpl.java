package com.fuyou.community.user.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.exception.ServiceException;
import com.fuyou.community.sys.util.PasswordUtil;
import com.fuyou.community.user.dao.UserMapper;
import com.fuyou.community.user.model.User;
import com.fuyou.community.user.model.dto.LoginDto;
import com.fuyou.community.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;

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
        return ResultVo.success(2000, "登录成功", tokenUid);
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
}
