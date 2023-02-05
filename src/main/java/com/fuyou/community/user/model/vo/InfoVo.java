package com.fuyou.community.user.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@ApiModel("用户信息")
public class InfoVo {
    private String name;
    private String avatar;
    private String introduction;
    private List<String> roles;
    private Map<String,List<String>> permission;
}