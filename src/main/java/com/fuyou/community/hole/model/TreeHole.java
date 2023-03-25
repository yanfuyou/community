package com.fuyou.community.hole.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fuyou.community.sys.model.DelBean;
import lombok.Data;

/**
 * 
 * @TableName TREE_HOLE
 */
@TableName("tree_hole")
@Data
public class TreeHole extends DelBean implements Serializable {
    /**
     * 发言id
     */
    private String id;

    /**
     * 内容
     */
    private String msg;

    /**
     * 是否公开
     */
    private String pub;

    private static final long serialVersionUID = 1L;

}