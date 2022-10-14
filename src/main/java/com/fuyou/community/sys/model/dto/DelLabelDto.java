package com.fuyou.community.sys.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("删除用户标签")
public class DelLabelDto {
    private String userId;
    private String labelId;

}
