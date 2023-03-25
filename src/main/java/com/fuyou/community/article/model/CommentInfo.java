package com.fuyou.community.article.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fuyou.community.sys.model.DelBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@TableName("comment_info")
@ApiModel("评论信息")
public class CommentInfo extends DelBean {
    @ApiModelProperty("评论id")
    @TableField("COMMENT_ID")
    private String commentId;

    @ApiModelProperty("父评论id")
    @TableField("PARENT_ID")
    private String parentId;

    @ApiModelProperty("内容")
    @TableField("CONTENT")
    @NotBlank(message = "内容不能为空")
    private String content;

    @ApiModelProperty("业务条线")
    @TableField("BIZ_TYPE")
    @NotBlank(message = "参数有误")
    private String bizType;

    @ApiModelProperty("所属内容id")
    @TableField("ARTICLE_ID")
    private String articleId;

    @ApiModelProperty("子评论")
    @TableField(exist = false)
    private List<CommentInfo> children;
}
