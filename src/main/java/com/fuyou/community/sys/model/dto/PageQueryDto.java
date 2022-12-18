package com.fuyou.community.sys.model.dto;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("分页查询")
@Data
public class PageQueryDto<T> {
    private Long current;
    private Long size;
    private List<OrderItem> orders;
    private T queryParam;
}
