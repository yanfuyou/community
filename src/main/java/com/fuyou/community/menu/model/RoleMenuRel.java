package com.fuyou.community.menu.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName role_menu_rel
 */
@TableName(value ="role_menu_rel")
@Data
public class RoleMenuRel implements Serializable {
    /**
     * 菜单名称
     */
    @TableField(value = "MENU_ID")
    private String menuId;

    /**
     * 父级ID
     */
    @TableField(value = "ROLE_ID")
    private String roleId;

    /**
     * 
     */
    @TableField(value = "FLAG")
    private String flag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}