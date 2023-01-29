package com.fuyou.community.team.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fuyou.community.team.model.TeamUserRel;
import com.fuyou.community.team.model.vo.UserTeam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamUserRelMapper extends BaseMapper<TeamUserRel> {
    List<UserTeam> getMyTeam(String userId);

    List<TeamUserRel> getTeamer(String teamId);
}
