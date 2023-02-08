package com.fuyou.community.role.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class RoleMenuDto {
    private String roleId;
    private List<String> menuIds;
}
