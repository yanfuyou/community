package com.fuyou.community.team.dao;

import com.fuyou.community.team.model.TeamInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yanfuyou
* @description 针对表【team_info】的数据库操作Mapper
* @createDate 2023-01-28 13:13:16
* @Entity com.fuyou.community.team.model.TeamInfo
*/
@Mapper
public interface TeamInfoMapper extends BaseMapper<TeamInfo> {
}




