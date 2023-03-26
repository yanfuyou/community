package com.fuyou.community.user.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangePwdDto {
    @NotNull(message = "id不能为空")
    private String userId;
    @NotNull(message = "旧密码不能为空")
    private String oldPwd;
    @NotNull(message = "新密码不能为空")
    private String newPwd;
}
