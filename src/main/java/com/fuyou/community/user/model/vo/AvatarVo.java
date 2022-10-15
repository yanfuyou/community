package com.fuyou.community.user.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户头像")
public class AvatarVo{
    @ApiModelProperty("用户id")
    private String id;
    @ApiModelProperty("头像链接")
    private String userAvatar;
}
