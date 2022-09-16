package com.fuyou.community.user.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fuyou.community.sys.model.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("USER")
@ApiModel(value = "用户实体类", description = "用户的基类")
public class User extends BaseBean {
    @TableId(value = "ID")
    @ApiModelProperty("用户id")
    private String id;

    @TableField("USER_NAME")
    @ApiModelProperty("用户名")
    private String userName;

    @TableField("USER_ALIAS")
    @ApiModelProperty("用户别名")
    private String userAlias;

    @TableField("USER_PASSWORD")
    @ApiModelProperty("用户密码")
    private String userPassword;

    @TableField("USER_SALT")
    @ApiModelProperty("密码盐值")
    private String userSalt;

    @TableField("USER_AVATAR")
    @ApiModelProperty("用户头像")
    private String userAvatar;

    @TableField("USER_STATUS")
    @ApiModelProperty("用户状态 1：删除 0：正常")
    private String userStatus;

}
