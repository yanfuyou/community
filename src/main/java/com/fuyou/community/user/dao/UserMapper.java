package com.fuyou.community.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.sys.model.PageDto;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import com.fuyou.community.user.model.User;
import com.fuyou.community.user.model.vo.AvatarVo;
import com.fuyou.community.user.model.vo.UserBasicVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author yanfuyou
 * @Description 用户dao层
 * @Date 下午8:50 2022/9/8
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    IPage<AvatarVo> getUserAvatars(Page<AvatarVo> page,@Param("avatarDto") PageDto<Set<String>> avatarDto);

    int score(@Param("userId") String userId,@Param("month") String month);

    Page<User> userMini(Page<User> page, @Param("dto")PageQueryDto<User> dto);

    Page<UserBasicVo> list(Page<UserBasicVo> page,@Param("dto") PageQueryDto<UserBasicVo> dto);
}
