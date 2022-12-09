package com.fuyou.community.article.model.dto;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fuyou.community.article.model.ArticleInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel("文章分页查询参数")
@Data
public class PageDto extends ArticleInfo {
    @NotNull(message = "当前页不能为空")
    private long current;
    @NotNull(message = "size不能为空")
    private long size;
    private List<OrderItem> orders;
}
