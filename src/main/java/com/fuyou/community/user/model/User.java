package com.fuyou.community.user.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("USER")
@ApiModel(value = "用户实体类", description = "用户的基类")
public class User {
    @TableId(value = "id")
    @ApiModelProperty("用户id")
    private String id;

    @TableField("user_name")
    @ApiModelProperty("用户名")
    private String userName;

    @TableField("user_alias")
    @ApiModelProperty("用户别名")
    private String userAlias;

    @TableField("user_password")
    @ApiModelProperty("用户密码")
    private String userPassword;

    @TableField("user_salt")
    @ApiModelProperty("密码盐值")
    private String userSalt;

    @TableField("user_status")
    @ApiModelProperty("用户状态 1：删除 0：正常")
    private String userStatus;

}
