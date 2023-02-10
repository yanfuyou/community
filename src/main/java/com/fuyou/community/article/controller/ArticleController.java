package com.fuyou.community.article.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.article.model.ArticleCover;
import com.fuyou.community.article.model.ArticleInfo;
import com.fuyou.community.article.model.dto.PageDto;
import com.fuyou.community.article.model.vo.ArticleHotVo;
import com.fuyou.community.article.model.vo.ArticleMiniVo;
import com.fuyou.community.article.model.vo.EnclVo;
import com.fuyou.community.article.service.ArticleService;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.exception.ServiceException;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/article")
@Api(tags = "文章的操作")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/release")
    @ApiOperation("发布文章")
    public ResultVo release(@RequestBody ArticleInfo articleInfo) {
        return articleService.release(articleInfo);
    }

    @GetMapping("/remove/{id}")
    public ResultVo remove(@PathVariable String id){
        return articleService.remove(id);
    }

    @PostMapping("/addCover")
    @ApiOperation("添加文章封面")
    public void addCover(@RequestBody ArticleCover articleCover) {
        articleService.addCover(articleCover);
    }

    @GetMapping("/getHots/{start}/{end}")
    @ApiOperation("获取热文-带封面的")
    public ResultVo<List<ArticleHotVo>> getHots(@PathVariable("start") String start, @PathVariable("end") String end) {
        return articleService.getHots(start, end);
    }

    @GetMapping("/getArticleInfo/{id}")
    @ApiOperation("获取文章信息")
    public ResultVo<ArticleInfo> getArticleInfo(@PathVariable String id) {
        try {
            return articleService.getArticleInfo(id);
        } catch (IOException e) {
            throw new ServiceException(5000, "获取文章内容失败！");
        }
    }

    @GetMapping("/enclInfo/{id}")
    @ApiOperation("获取附件信息")
    public ResultVo<EnclVo> getEnclInfo(@PathVariable String id) {
        return articleService.getEnclInfo(id);
    }

    @PostMapping("/miniList")
    @ApiOperation("文章mini视图列表")
    public ResultVo<Page<ArticleMiniVo>> getArticlePage(@Validated @RequestBody PageDto dto) {
        return articleService.miniList(dto);
    }

    @GetMapping("/readPlusOne/{id}")
    @ApiOperation("增加阅读量")
    public ResultVo<Object> readPlusOne(@PathVariable String id){
        return articleService.readPlusOne(id);
    }

    @PostMapping("/admin/list")
    @ApiOperation("列表查询")
    public ResultVo<Page<ArticleInfo>> list(@RequestBody PageDto pageDto){
        return articleService.list(pageDto);
    }

    @PostMapping("/search")
    public ResultVo<Page<ArticleMiniVo>> pageSearch(@RequestBody PageQueryDto<ArticleMiniVo> dto){
        return articleService.pageSearch(dto);
    }

    @GetMapping("/enable/{id}")
    public ResultVo<Object> enable(@PathVariable @NotNull String id){
        articleService.update(Wrappers.lambdaUpdate(ArticleInfo.class)
                .eq(ArticleInfo::getId,id)
                .set(ArticleInfo::getFlag,'0'));
        return ResultVo.success(2000,"操作成功");
    }
}
