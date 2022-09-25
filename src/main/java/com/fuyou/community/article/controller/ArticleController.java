package com.fuyou.community.article.controller;

import com.fuyou.community.article.model.ArticleInfo;
import com.fuyou.community.article.service.ArticleService;
import com.fuyou.community.common.ResultVo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@Api("文章的控制类")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/release")
    public ResultVo release(@RequestBody ArticleInfo articleInfo){
        return articleService.release(articleInfo);
    }
}
