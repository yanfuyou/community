package com.fuyou.community.article.model.vo;

import com.fuyou.community.article.model.ArticleInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("热文")
@Data
public class ArticleHotVo extends ArticleInfo {
    @ApiModelProperty("得分")
    private String score;
    @ApiModelProperty("封面路径")
    private String coverPath;
}
