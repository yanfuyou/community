package com.fuyou.community.article.model.vo;

import com.fuyou.community.sys.model.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("附件信息")
@Data
public class EnclVo extends BaseBean {
    @ApiModelProperty("文章id")
    private String articleId;
    @ApiModelProperty("附件id")
    private String fileId;
    @ApiModelProperty("下载地址")
    private String downPath;
    @ApiModelProperty("文件名")
    private String fileName;

}