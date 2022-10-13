package com.fuyou.community.sys.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("查询参数")
@Data
public class QueryDto {
    private String current;

    private String size;

    private String type;

    private String flag;
}
