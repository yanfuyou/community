package com.fuyou.community.article.controller;

import com.fuyou.community.article.model.CommentInfo;
import com.fuyou.community.article.service.CommentService;
import com.fuyou.community.common.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论信息")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/release")
    @ApiOperation("发布评论")
    public ResultVo release(@RequestBody @Validated CommentInfo commentInfo){
        return commentService.release(commentInfo);
    }
}