package com.fuyou.community.article.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fuyou.community.sys.model.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@TableName("article_score")
@ApiModel("文章评分")
@Data
public class ArticleScore extends BaseBean {
    @ApiModelProperty("用户id")
    @TableField("USER_ID")
    private String userId;
    @TableField("ARTICLE_ID")
    @ApiModelProperty("文章id")
    private String ArticleId;
    @TableField("SCORE")
    @ApiModelProperty("得分")
    private String score;
}
