package com.fuyou.community.user.model.vo;

import com.fuyou.community.role.model.RoleInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("用户信息")
public class InfoVo {
    private String name;
    private String avatar;
    private String introduction;
    private List<String> roles;
}