package com.fuyou.community.sys.util;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.fuyou.community.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class CurrentUtil {

    private final RedisTemplate<String,Object> redisTemplate;
    /**
     * 当登录的用户
     */
    private static User loginUser;

    /**
     * @Author yanfuyou
     * @Description 获取当前项目的路径
     * @Date 下午6:49 2022/9/18
     * @Return 项目路径
     */
    public static String getProjectPath(){
        return System.getProperty("user.dir");
    }
    public static User getLoginUser(){
        String loginDevice = StpUtil.getTokenInfo().getLoginDevice();
        return JSON.parseObject(loginDevice, User.class);
    }
}
