package com.fuyou.community.menu.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import com.fuyou.community.sys.model.DelBean;
import lombok.Data;

/**
 * 
 * @TableName menu_info
 */
@TableName(value ="menu_info")
@Data
public class MenuInfo extends DelBean implements Serializable {
    /**
     * 
     */
    @TableId(value = "ID")
    private String id;

    /**
     * 菜单名称
     */
    @TableField(value = "MENU_NAME")
    private String menuName;

    /**
     * 父级ID
     */
    @TableField(value = "PARENT_ID")
    private String parentId;

    @TableField(value = "PERMISSION")
    private String permission;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<MenuInfo> children;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}