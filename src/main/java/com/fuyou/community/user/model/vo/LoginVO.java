package com.fuyou.community.user.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginVO {
    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户别名-昵称")
    private String userAlias;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("登录token")
    private String tokenId;

}
