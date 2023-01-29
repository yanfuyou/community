package com.fuyou.community.team.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import com.fuyou.community.team.model.TeamInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fuyou.community.team.model.TeamUserRel;
import com.fuyou.community.team.model.vo.UserTeam;

import java.util.List;

/**
* @author yanfuyou
* @description 针对表【team_info】的数据库操作Service
* @createDate 2023-01-28 13:13:16
*/
public interface TeamInfoService extends IService<TeamInfo> {
    ResultVo add(TeamInfo teamInfo);

    ResultVo del(String id);

    ResultVo<Page<TeamInfo>> list(PageQueryDto<TeamInfo> teamDto);

    ResultVo addRel(TeamUserRel teamUserRel);

    ResultVo delRel(String id);

    List<UserTeam> getMyTeam(String userId);

    List<TeamUserRel> getTeamer(String teamId);
}
