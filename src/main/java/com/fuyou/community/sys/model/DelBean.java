package com.fuyou.community.sys.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("加入删除标记")
@Data
public class DelBean extends BaseBean{
    @ApiModelProperty("删除标记")
    private Integer flag;
}
