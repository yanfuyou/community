package com.fuyou.community.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@TableName("SYS_LABELINFO")
@Data
@ApiModel("系统标签")
public class SysLabelinfo extends BaseBean{
    @TableId("ID")
    @ApiModelProperty("标签id")
    private String id;

    @TableField("LABEL_NAME")
    @ApiModelProperty("标签名")
    private String labelName;

    @TableField("LABEL_TYPE")
    @ApiModelProperty("标签类型")
    private String labelType;

    @TableField("FLAG")
    @ApiModelProperty("删除标记 1：是 0：否 ")
    private String flag;
}
