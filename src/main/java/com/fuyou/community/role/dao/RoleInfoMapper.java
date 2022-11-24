package com.fuyou.community.role.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fuyou.community.role.model.RoleInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author yanfuyou
* @description 针对表【ROLE_INFO】的数据库操作Mapper
* @createDate 2022-11-24 23:37:26
* @Entity com.fuyou.community.role.model.RoleInfo
*/
public interface RoleInfoMapper extends BaseMapper<RoleInfo> {
    List<RoleInfo> getRoleList(String userId);
}
