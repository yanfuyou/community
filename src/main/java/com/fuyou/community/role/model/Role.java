package com.fuyou.community.role.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("ROLE")
@TableName("ROLE")
public class Role {

    @ApiModelProperty("角色id")
    @TableField("ID")
    private String id;

    @ApiModelProperty("角色名称")
    @TableField("ROLE_NAME")
    private String roleNAme;

}
