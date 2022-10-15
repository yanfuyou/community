package com.fuyou.community.sys.model;

import cn.hutool.db.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageDto<T> extends Page {
    private static final long serialVersionUID = -124050135302877901L;
    @ApiModelProperty("当前页")
    private int current;
    @ApiModelProperty("每页数量")
    private int size;
    @ApiModelProperty("请求参数")
    private T params;
}
