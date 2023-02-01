package com.fuyou.community.collect.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.article.model.vo.ArticleMiniVo;
import com.fuyou.community.collect.model.Collect;
import com.fuyou.community.collect.service.CollectService;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/collect")
@RequiredArgsConstructor
@Api(tags = "用户收藏")
public class CollectController {
    private final CollectService collectService;

    @PostMapping("/save")
    @ApiOperation("添加收藏")
    public ResultVo<Object> save(@RequestBody Collect collect) {
        if (StrUtil.isBlank(collect.getId())) {
            collect.setId(IdUtil.simpleUUID());
        }
        boolean b = collectService.saveOrUpdate(collect);
        return b ? ResultVo.success(2000, "收藏成功") : ResultVo.fail(5000, "收藏失败");
    }

    @PostMapping("/page")
    @ApiOperation("获取用户收藏信息")
    public ResultVo<Page<ArticleMiniVo>> page(@RequestBody PageQueryDto<ArticleMiniVo> collectPageQueryDto) {
        return ResultVo.success(2000, "查询收藏成功", collectService.myCollect(collectPageQueryDto));
    }

    @GetMapping("/collected/{userId}/{relId}")
    @ApiOperation("用户是否关注该内容")
    public ResultVo<String> collected(@PathVariable String userId, @PathVariable String relId) {
        Collect one = collectService.getOne(Wrappers.lambdaQuery(Collect.class)
                .eq(Collect::getUserId, userId)
                .eq(Collect::getRelId, relId)
                .eq(Collect::getFlag, '0'));
        if (ObjectUtil.isNotEmpty(one)) {
            return ResultVo.success(2000, "已收藏", one.getId());
        }
        return ResultVo.success(2000, "已收藏", null);
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation("删除用户收藏信息")
    public ResultVo<Object> del(@PathVariable @NotBlank(message = "参数异常") String id) {
        boolean remove = collectService.remove(Wrappers.lambdaQuery(Collect.class)
                .eq(Collect::getId, id));
        return remove ? ResultVo.success(2000, "删除成功") : ResultVo.fail(5000, "删除失败");
    }
}
