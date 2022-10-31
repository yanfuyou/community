package com.fuyou.community.article.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fuyou.community.sys.model.DelBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@TableName("COMMON_INFO")
@ApiModel("评论信息")
public class CommonInfo extends DelBean {
    @ApiModelProperty("引用内容id")
    private String relId;

    @ApiModelProperty("评论id")
    private String commonId;

    @ApiModelProperty("父评论id")
    private String parentId;

    @ApiModelProperty("内容")
    private String Content;

    @ApiModelProperty("子评论信息")
    private List<CommonInfo> children;
}
