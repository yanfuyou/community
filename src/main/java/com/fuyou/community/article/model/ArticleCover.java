package com.fuyou.community.article.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fuyou.community.sys.model.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("文章封面")
@TableName("ARTICLE_COVER")
@Data
public class ArticleCover extends BaseBean {
    @TableField("ARTICLE_ID")
    @ApiModelProperty("文章id")
    private String articleId;

    @TableField("COVER_PATH")
    @ApiModelProperty("封面链接")
    private String coverPath;
}
