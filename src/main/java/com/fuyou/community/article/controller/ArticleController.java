package com.fuyou.community.article.controller;

import com.fuyou.community.article.model.ArticleCover;
import com.fuyou.community.article.model.ArticleInfo;
import com.fuyou.community.article.model.vo.ArticleHotVo;
import com.fuyou.community.article.model.vo.EnclVo;
import com.fuyou.community.article.service.ArticleService;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("/getArticleInfo/{id}")
    @ApiOperation("获取文章信息")
    public ResultVo<ArticleInfo> getArticleInfo(@PathVariable String id){
        try {
            return articleService.getArticleInfo(id);
        }catch (IOException e){
            throw new ServiceException(5000,"获取文章内容失败！");
        }
    }

    @GetMapping("/enclInfo/{id}")
    @ApiOperation("获取附件信息")
    public ResultVo<EnclVo> getEnclInfo(@PathVariable String id){
        return articleService.getEnclInfo(id);
    }
}
