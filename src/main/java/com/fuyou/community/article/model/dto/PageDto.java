package com.fuyou.community.article.model.dto;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fuyou.community.article.model.ArticleInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("文章分页查询参数")
@Data
public class PageDto extends ArticleInfo {
    private long current;
    private long size;
    private List<OrderItem> orders;
}
