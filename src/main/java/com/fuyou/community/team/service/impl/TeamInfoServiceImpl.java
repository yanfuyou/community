package com.fuyou.community.team.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.exception.ServiceException;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import com.fuyou.community.team.dao.TeamUserRelMapper;
import com.fuyou.community.team.model.TeamInfo;
import com.fuyou.community.team.model.TeamUserRel;
import com.fuyou.community.team.model.vo.UserTeam;
import com.fuyou.community.team.service.TeamInfoService;
import com.fuyou.community.team.dao.TeamInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author yanfuyou
 * @description 针对表【team_info】的数据库操作Service实现
 * @createDate 2023-01-28 13:13:16
 */
@Service
@RequiredArgsConstructor
public class TeamInfoServiceImpl extends ServiceImpl<TeamInfoMapper, TeamInfo>
        implements TeamInfoService {

    private final TeamInfoMapper teamInfoMapper;
    private final TeamUserRelMapper teamUserRelMapper;

    @Override
    public ResultVo add(TeamInfo teamInfo) {
        if (ObjectUtil.isNull(teamInfo)) {
            throw new ServiceException(5000, "参数异常");
        }
        teamInfo.setId(IdUtil.simpleUUID());
        teamInfoMapper.insert(teamInfo);
        return ResultVo.success(2000, "发布成功！");
    }

    @Override
    public ResultVo del(String id) {
        if (StrUtil.isBlank(id)) {
            throw new ServiceException(5000, "参数异常！");
        }
        TeamInfo teamInfo = new TeamInfo();
        teamInfo.setId(id).setFlag(1);
        teamInfoMapper.updateById(teamInfo);
        return ResultVo.success(2000, "取消成功");
    }

    @Override
    public ResultVo<Page<TeamInfo>> list(PageQueryDto<TeamInfo> teamDto) {
        if (ObjectUtil.isNull(teamDto)){
            throw new ServiceException(5000,"参数异常！");
        }
        Page<TeamInfo> page = new Page<>();
        page.setCurrent(teamDto.getCurrent());
        page.setSize(teamDto.getSize());
        page.setOrders(teamDto.getOrders());
        Page<TeamInfo> teamInfoPage = teamInfoMapper.selectPage(page, Wrappers.lambdaQuery(TeamInfo.class).eq(TeamInfo::getFlag, teamDto.getQueryParam().getFlag()) );
        return ResultVo.success(2000, "查询成功", teamInfoPage);
    }

    @Override
    public ResultVo addRel(TeamUserRel teamUserRel) {
        if (ObjectUtil.isNull(teamUserRel)) {
            throw new ServiceException(5000, "参数异常！");
        }
        Integer integer = teamUserRelMapper.selectCount(Wrappers.lambdaQuery(TeamUserRel.class)
                .eq(TeamUserRel::getUserId, teamUserRel.getUserId())
                .eq(TeamUserRel::getTeamId, teamUserRel.getTeamId()));
        if (integer > 0){
            throw new ServiceException(5000,"你已报名当前队伍！");
        }
        teamUserRel.setId(IdUtil.simpleUUID());
        teamUserRelMapper.insert(teamUserRel);
        return ResultVo.success(2000, "插入成功");
    }

    @Override
    public ResultVo delRel(String id) {
        if (StrUtil.isBlank(id)) {
            throw new ServiceException(5000, "参数异常！");
        }
        TeamUserRel teamUserRel = new TeamUserRel();
        teamUserRel.setId(id).setFlag(1);
        teamUserRelMapper.updateById(teamUserRel);
        return ResultVo.success(2000, "退出成功。");
    }

    @Override
    public List<UserTeam> getMyTeam(String userId) {
        return teamUserRelMapper.getMyTeam(userId);
    }

    @Override
    public List<TeamUserRel> getTeamer(String teamId) {
        return teamUserRelMapper.getTeamer(teamId);
    }
}




