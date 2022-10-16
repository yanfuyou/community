package com.fuyou.community.article.controller;

import com.fuyou.community.article.model.ArticleScore;
import com.fuyou.community.article.service.ArticleScoreService;
import com.fuyou.community.common.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "文章得分")
@CrossOrigin
@RestController
@RequestMapping("/score")
@RequiredArgsConstructor
public class ArticleScoreController {
    private final ArticleScoreService scoreService;

    @GetMapping("/getArtScore/{userId}/{artId}")
    @ApiOperation("获取用户文章评分")
    public ResultVo<List<ArticleScore>> getArtScore(@PathVariable String userId,@PathVariable String artId){
        return scoreService.getArtScore(userId,artId);
    }

    @PostMapping("/addScore")
    @ApiOperation("文章评分")
    public ResultVo addScore(@RequestBody ArticleScore score){
        return scoreService.addScore(score);
    }
}
