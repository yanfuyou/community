package com.fuyou.community.collect.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.fuyou.community.sys.model.DelBean;
import lombok.Data;

/**
 * @TableName COLLECT
 */
@TableName(value = "collect")
@Data
public class Collect extends DelBean implements Serializable {
    /**
     * ID
     */
    @TableId(value = "ID")
    private String id;

    /**
     * 用户id
     */
    @TableField(value = "USER_ID")
    private String userId;

    /**
     * 收藏的内容id
     */
    @TableField(value = "REL_ID")
    private String relId;

    /**
     * 内容类型，1:文章2:资料
     */
    @TableField(value = "BIZ_TYPE")
    private String bizType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}