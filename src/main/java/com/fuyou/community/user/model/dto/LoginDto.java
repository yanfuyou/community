package com.fuyou.community.user.model.dto;

import com.fuyou.community.user.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ApiModel("用户登录请求实体")
@Data
@ToString(callSuper = true)
public class LoginDto extends User {
    @ApiModelProperty("验证码")
    private String verification;
    @ApiModelProperty("验证码key")
    private String codeUid;
    @ApiModelProperty("是否需要验证码")
    private String codeFlag;
}
