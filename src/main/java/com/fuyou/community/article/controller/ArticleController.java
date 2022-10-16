package com.fuyou.community.article.controller;

import com.fuyou.community.article.model.ArticleCover;
import com.fuyou.community.article.model.ArticleInfo;
import com.fuyou.community.article.model.vo.ArticleHotVo;
import com.fuyou.community.article.service.ArticleService;
import com.fuyou.community.common.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@Api(tags = "文章的控制类")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/release")
    @ApiOperation("发布文章")
    public ResultVo release(@RequestBody ArticleInfo articleInfo){
        return articleService.release(articleInfo);
    }

    @PostMapping("/addCover")
    @ApiOperation("添加文章封面")
    public void addCover(@RequestBody ArticleCover articleCover){
        articleService.addCover(articleCover);
    }

    @GetMapping("/getHots/{start}/{end}")
    @ApiOperation("获取热文-带封面的")
    public ResultVo<List<ArticleHotVo>> getHots(@PathVariable("start")String start, @PathVariable("end")String end){
        return articleService.getHots(start,end);
    }
}
