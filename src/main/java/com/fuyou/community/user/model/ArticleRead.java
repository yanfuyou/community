package com.fuyou.community.user.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ARTICLE_READ")
public class ArticleRead {
    @TableField("ARTICLE_ID")
    private String articleId;

    @TableField("READ_COUNT")
    private String readCount;
}
