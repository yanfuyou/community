package com.fuyou.community.article.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.article.model.CommentInfo;
import com.fuyou.community.article.service.CommentService;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.sys.model.dto.PageQueryDto;
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

    @PostMapping("/pageList")
    @ApiOperation("获取评论列表")
    public ResultVo<Page<CommentInfo>> pageList(@RequestBody PageQueryDto<CommentInfo> dto){
        return commentService.pageList(dto);
    }

    @PostMapping("/update")
    public ResultVo<Object> update(@RequestBody CommentInfo commentInfo){
        commentService.update(commentInfo, Wrappers.lambdaUpdate(CommentInfo.class)
                .eq(CommentInfo::getCommentId,commentInfo.getCommentId()));
        return ResultVo.success(2000,"更新成功");
    }

    @GetMapping("/getCommentInfo/{id}")
    public ResultVo<CommentInfo> getCommentInfo(@PathVariable String id) {
        CommentInfo one = commentService.getOne(Wrappers.lambdaQuery(CommentInfo.class)
                .eq(CommentInfo::getCommentId, id));
        return ResultVo.success(2000,"获取成功",one);
    }
}