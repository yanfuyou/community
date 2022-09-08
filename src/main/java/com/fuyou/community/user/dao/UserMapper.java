package com.fuyou.community.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fuyou.community.user.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yanfuyou
 * @Description 用户dao层
 * @Date 下午8:50 2022/9/8
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
