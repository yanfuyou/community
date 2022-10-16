package com.fuyou.community.user.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fuyou.community.sys.model.BaseBean;
import lombok.Data;

@Data
@TableName("ARTICLE_FOLLOW")
public class ArticleFollow extends BaseBean {
    @TableField("ARTICLE_ID")
    private String articleId;
    @TableField("USER_ID")
    private String userId;
    @TableField("FLAG")
    private String flag;
}
