package com.fuyou.community.team.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import com.fuyou.community.team.model.TeamInfo;
import com.fuyou.community.team.model.TeamUserRel;
import com.fuyou.community.team.model.vo.UserTeam;
import com.fuyou.community.team.service.TeamInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("队伍相关操作")
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamInfoService teamInfoService;

    @ApiOperation("发布队伍")
    @PostMapping("/add")
    public ResultVo add(@RequestBody TeamInfo teamInfo){
        return teamInfoService.add(teamInfo);
    }

    @ApiOperation("删除")
    @GetMapping("/del/{id}")
    public ResultVo del(@PathVariable String id){
        return teamInfoService.del(id);
    }

    @ApiOperation("获取队伍列表")
    @PostMapping("/list")
    public ResultVo<Page<TeamInfo>> list(@RequestBody PageQueryDto<TeamInfo> teamDto){
        return teamInfoService.list(teamDto);
    }

    /**
     * @Author yanfuyou
     * @Description 以下是用户加入队伍相关方法
     * @Date 下午8:36 2023/1/29
     */

    @ApiOperation("加入队伍")
    @PostMapping("/addRel")
    public ResultVo addRel(@RequestBody TeamUserRel teamUserRel){
        return teamInfoService.addRel(teamUserRel);
    }

    @ApiOperation("退出队伍")
    @GetMapping("/delRel/{id}")
    public ResultVo delRel(@PathVariable String id){
        return teamInfoService.delRel(id);
    }

    @GetMapping("/getMyTeam/{userId}")
    public ResultVo<List<UserTeam>> getMyTeam(@PathVariable String userId){
        List<UserTeam> myTeam = teamInfoService.getMyTeam(userId);
        return ResultVo.success(2000,"获取队伍信息成功！",myTeam);
    }

    @GetMapping("/getTeamer/{teamId}")
    public ResultVo<List<TeamUserRel>> getTeamer(@PathVariable String teamId){
        List<TeamUserRel> teamer = teamInfoService.getTeamer(teamId);
        return ResultVo.success(2000,"查询成功",teamer);
    }
}
