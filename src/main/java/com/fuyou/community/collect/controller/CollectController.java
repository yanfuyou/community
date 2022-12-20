package com.fuyou.community.collect.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fuyou.community.collect.model.Collect;
import com.fuyou.community.collect.service.CollectService;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collect")
@RequiredArgsConstructor
@Api(tags = "用户收藏")
public class CollectController {
    private final CollectService collectService;

    @PostMapping("/save")
    @ApiOperation("添加收藏")
    public void save(@RequestBody Collect collect) {
        collect.setId(IdUtil.simpleUUID());
        collectService.save(collect);
    }

    @PostMapping("/page")
    @ApiOperation("获取用户收藏信息")
    public void page(@RequestBody PageQueryDto<Collect> collectPageQueryDto) {

    }

    @GetMapping("/del/{id}")
    @ApiOperation("删除用户收藏信息")
    public ResultVo<Object> del(@PathVariable String id) {
        boolean remove = collectService.remove(Wrappers.lambdaQuery(Collect.class)
                .eq(Collect::getId, id));
        return remove ? ResultVo.success(2000, "删除成功") : ResultVo.fail(5000, "删除失败");
    }
}
