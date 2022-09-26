package com.fuyou.community.user.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户基本信息")
@Data
public class BaseInfoDto {
    @ApiModelProperty("用户id")
    private String id;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("别名-昵称")
    private String userAlias;
    @ApiModelProperty("生日")
    private String birthday;
    @ApiModelProperty("性别")
    private String userSex;
    @ApiModelProperty("个签")
    private String userSign;
}
