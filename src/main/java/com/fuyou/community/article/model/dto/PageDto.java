package com.fuyou.community.article.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("文章分页查询参数")
public class PageDto {
    @ApiModelProperty("文章id")
    private String articleId;
    @ApiModelProperty("用户id")
    private String userId;

}
