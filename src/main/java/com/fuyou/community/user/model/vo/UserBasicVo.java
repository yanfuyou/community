package com.fuyou.community.user.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fuyou.community.sys.model.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户基本信息")
@Data
public class UserBasicVo extends BaseBean {
    @ApiModelProperty("用户id")
    private String id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户别名")
    private String userAlias;

    @ApiModelProperty("用户性别1：男 0：女")
    private String userSex;

    @ApiModelProperty("用户生日")
    private String birthday;

    @ApiModelProperty("想说/签名")
    private String userSign;

    @ApiModelProperty("用户头像")
    private String userAvatar;

}
