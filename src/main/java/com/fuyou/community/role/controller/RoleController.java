package com.fuyou.community.role.controller;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.exception.ServiceException;
import com.fuyou.community.menu.model.MenuInfo;
import com.fuyou.community.role.model.RoleInfo;
import com.fuyou.community.role.model.dto.RoleMenuDto;
import com.fuyou.community.role.service.RoleInfoService;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@Api(tags = "角色")
public class RoleController {
    private final RoleInfoService roleInfoService;

    @PostMapping("/add")
    public ResultVo<Object> add(@RequestBody RoleInfo roleInfo){
        return roleInfoService.add(roleInfo);
    }

    @PostMapping("/page")
    public ResultVo<Page<RoleInfo>> page(@RequestBody PageQueryDto<RoleInfo> dto){
        Page<RoleInfo> page = new Page<>();
        page.setCurrent(dto.getCurrent()).setSize(dto.getSize()).setOrders(dto.getOrders());
        Page<RoleInfo> pageInfo = roleInfoService.page(page, Wrappers.lambdaQuery(RoleInfo.class)
                .eq(RoleInfo::getFlag, dto.getQueryParam().getFlag()));
        return ResultVo.success(2000,"获取角色信息成功",pageInfo);
    }
    @PostMapping("/update")
    public ResultVo<Object> update(@RequestBody RoleInfo roleInfo){
        roleInfoService.updateById(roleInfo);
        return ResultVo.success(2000,"更新成功");
    }

    @GetMapping("/getRoleMenu/{roleId}")
    public ResultVo<List<String>> getRoleMenu(@PathVariable String roleId){
        List<String> menuIds = roleInfoService.roleMenu(roleId).stream().map(MenuInfo::getId).collect(Collectors.toList());
        return ResultVo.success(2000,"获取角色菜单成功",menuIds);
    }

    @PostMapping("/addRoleMenu")
    public ResultVo addRoleMenu(@RequestBody RoleMenuDto dto){
//        为空则剔除所有菜单
//        if (CollUtil.isEmpty(dto.getMenuIds())){
//            throw new ServiceException(5000,"参数异常");
//        }
        roleInfoService.addRoleMenu(dto.getRoleId(), dto.getMenuIds());
        return ResultVo.success(2000,"添加成功");
    }

    @GetMapping("/rel/change/{userId}/{roleId}")
    @ApiOperation("修改用户角色引用")
    public ResultVo<Object> changeRel(@PathVariable @NotNull String userId,@PathVariable @NotNull String roleId){
        return roleInfoService.addUserRoleRel(userId,roleId);
    }

    @GetMapping("/beAdmin/{userId}")
    @ApiOperation("设置为管理员")
    public ResultVo<Object> beAdmin(@PathVariable @NotNull String userId){
        return roleInfoService.beAdmin(userId);
    }
}
