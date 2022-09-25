package com.fuyou.community.article.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fuyou.community.sys.model.BaseBean;
import lombok.Data;

@Data
@TableName("ARTICLE_FILE_REL")
public class ArticleFileRel extends BaseBean {
    @TableField("ID")
    private String id;
    @TableField("ARTICLE_ID")
    private String articleId;
    @TableField("FILE_ID")
    private String fileId;
}
