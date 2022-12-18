package com.fuyou.community.hole.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.hole.model.TreeHole;
import com.fuyou.community.hole.model.vo.HoleVo;
import com.fuyou.community.hole.service.TreeHoleService;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "树洞")
@RestController
@RequestMapping("/hole")
@RequiredArgsConstructor
public class TreeHoleController {
    private final TreeHoleService treeHoleService;

    @PostMapping("/page")
    @ApiOperation("分页查询")
    public ResultVo<Page<HoleVo>> page(@RequestBody PageQueryDto<TreeHole> hole){
        return treeHoleService.holePage(hole);
    }

    @PostMapping("/update")
    @ApiOperation("添加|更新")
    public ResultVo<Object> update(@RequestBody TreeHole treeHole){
        if (StrUtil.isBlank(treeHole.getId())){
            treeHole.setId(IdUtil.simpleUUID());
        }
        treeHole.setUpdateBy(null);
        treeHole.setCreateBy(null);
        treeHole.setCreateTime(null);
        treeHole.setUpdateTime(null);
        boolean b = treeHoleService.saveOrUpdate(treeHole, Wrappers.lambdaUpdate(TreeHole.class)
                .eq(TreeHole::getId, treeHole.getId()));
        return b ? ResultVo.success(2000,"成功") : ResultVo.fail(5000,"失败");
    }
    @GetMapping("/del/{id}")
    public ResultVo<Object> del(@PathVariable String id){
        boolean remove = treeHoleService.remove(Wrappers.lambdaQuery(TreeHole.class)
                .eq(TreeHole::getId, id));
        return remove ? ResultVo.success(2000,"删除成功") : ResultVo.fail(5000,"删除失败");
    }
}
