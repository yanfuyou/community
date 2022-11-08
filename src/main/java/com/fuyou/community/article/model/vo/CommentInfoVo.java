package com.fuyou.community.article.model.vo;

import com.fuyou.community.article.model.CommentInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("评论列表")
public class CommentInfoVo extends CommentInfo {
    @ApiModelProperty("子评论信息")
    private List<CommentInfoVo> children;
}
