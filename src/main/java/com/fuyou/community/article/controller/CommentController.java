package com.fuyou.community.article.controller;

import com.fuyou.community.article.model.CommentInfo;
import com.fuyou.community.article.service.CommentService;
import com.fuyou.community.common.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论信息")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/release")
    @ApiOperation("发布评论")
    public ResultVo release(@RequestBody @Validated CommentInfo commentInfo) {
        return commentService.release(commentInfo);
    }

    @PostMapping("/getArticleCommentRoots")
    @ApiOperation("获取文章的父级评论")
    public ResultVo<List<CommentInfo>> getArticleCommentRoots(@RequestBody CommentInfo commentInfo) {
        return commentService.list(commentInfo);
    }

    @ApiOperation("获取评论树")
    @GetMapping("/getCommentTree/{articleId}")
    public ResultVo<List<CommentInfo>> getCommentTree(@PathVariable String articleId) {
        return commentService.commentTree(articleId);
    }

    @ApiOperation("删除评论")
    @GetMapping("/del/{commentId}")
    public ResultVo delComment(@PathVariable String commentId){
        return commentService.delComment(commentId);
    }
}