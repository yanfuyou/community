package com.fuyou.community.file.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fuyou.community.sys.model.BaseBean;
import com.fuyou.community.sys.model.DelBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("FILE_INFO")
public class FileInfo extends DelBean {
    @ApiModelProperty("文件id")
    @TableId("ID")
    private String id;

    @ApiModelProperty("用户id")
    @TableField("USER_ID")
    private String userId;

    @ApiModelProperty("文件保存名称")
    @TableField("SAVE_NAME")
    private String saveName;

    @ApiModelProperty("文件原始名称")
    @TableField("FILE_NAME")
    private String fileName;

    @ApiModelProperty("文件访问路径")
    @TableField("VISIT_PATH")
    private String visitPath;

    @ApiModelProperty("文件状态")
    @TableField("FILE_STATUS")
    private String fileStatus;

    @ApiModelProperty("文件类型")
    @TableField("BIZ_TYPE")
    private String bizType;
}
