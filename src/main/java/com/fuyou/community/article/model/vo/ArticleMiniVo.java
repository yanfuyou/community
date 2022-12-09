package com.fuyou.community.article.model.vo;

import com.fuyou.community.article.model.ArticleInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("文章缩略视图")
@Data
public class ArticleMiniVo extends ArticleInfo {
    @ApiModelProperty("阅读量")
    private int readCount;
    @ApiModelProperty("总得分")
    private int scoreCount;
    @ApiModelProperty("评论数")
    private int commCount;
}
