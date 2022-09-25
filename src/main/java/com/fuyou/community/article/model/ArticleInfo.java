package com.fuyou.community.article.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fuyou.community.sys.model.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("文章信息")
@TableName("ARTICLE_INFO")
public class ArticleInfo extends BaseBean implements Serializable {
    private static final long serialVersionUID = -5604742465445529984L;

    @TableField("ID")
    @ApiModelProperty("文章id")
    private String id;

    @TableField("ARTICLE_NAME")
    @ApiModelProperty("文章名、主题")
    private String articleName;

    @TableField("ARTICLE_content")
    @ApiModelProperty("文章内容，目前考虑存路径")
    private String articleContent;

    @TableField("ENCLOSURE")
    @ApiModelProperty("是否有附件")
    private String enclosure;

    @TableField("ARTICLE_LABELS")
    @ApiModelProperty("文章标签信息")
    private String articleLabels;

    @TableField("USER_ID")
    @ApiModelProperty("发表者")
    private String userId;
}
