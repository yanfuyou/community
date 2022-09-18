package com.fuyou.community.sys.util;

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
     * @Author yanfuyou
     * @Description 获取当前项目的路径
     * @Date 下午6:49 2022/9/18
     * @Return 项目路径
     */
    public static String getProjectPath(){
        return System.getProperty("user.dir");
    }

    public User getUserInfo(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        if (StrUtil.isEmpty(authorization)){
            return null;
        }
        String userJson = (String)redisTemplate.opsForValue().get(authorization);
        if (StrUtil.isEmpty(userJson)){
            return null;
        }
        return JSON.parseObject(userJson, User.class);
    }
}
