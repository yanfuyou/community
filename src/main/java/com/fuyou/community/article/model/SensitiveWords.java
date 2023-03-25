package com.fuyou.community.article.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("sensitive_words")
@Data
public class SensitiveWords {
    @TableId("id")
    private String id;
    @TableField("WORD")
    private String word;
    @TableField("CATEGORY")
    private String category;
}
