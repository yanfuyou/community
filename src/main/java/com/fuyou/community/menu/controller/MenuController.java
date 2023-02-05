package com.fuyou.community.menu.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.menu.model.MenuInfo;
import com.fuyou.community.menu.service.MenuInfoService;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/menu")
@Api(tags = "路由菜单")
@RequiredArgsConstructor
public class MenuController {
    private final MenuInfoService menuInfoService;

    @PostMapping("/addMenu")
    public ResultVo<Object> addMenu(@RequestBody MenuInfo menuInfo) {
        return menuInfoService.addMenu(menuInfo);
    }

    @PostMapping("/updateMenu")
    public ResultVo<Object> updateMenu(@RequestBody MenuInfo menuInfo) {
        menuInfoService.update(menuInfo, Wrappers.lambdaUpdate(MenuInfo.class)
                .eq(MenuInfo::getId,menuInfo.getId()));
        return ResultVo.success(2000,"更新成功");
    }

    @PostMapping("/menuList")
    public ResultVo<Page<MenuInfo>> menuList(@RequestBody PageQueryDto<MenuInfo> dto){
        Page<MenuInfo> page = new Page<>();
        page.setCurrent(dto.getCurrent()).setSize(dto.getSize()).setOrders(dto.getOrders());
        Page<MenuInfo> pageMenu = menuInfoService.page(page,Wrappers.lambdaQuery(MenuInfo.class)
                .eq(MenuInfo::getFlag,"0"));
        return ResultVo.success(2000,"获取成功",pageMenu);
    }
    @GetMapping("/tree")
    public ResultVo<List<MenuInfo>> tree(){
        return menuInfoService.tree();
    }
}
