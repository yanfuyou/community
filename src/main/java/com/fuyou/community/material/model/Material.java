package com.fuyou.community.material.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.fuyou.community.sys.model.DelBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 
 * @TableName material 资料实体类
 */
@TableName(value ="material")
@Data
@Accessors(chain = true)
@ApiModel
public class Material extends DelBean implements Serializable {
    /**
     * ID
     */
    @TableId(value = "ID")
    private String id;

    /**
     * 描述
     */
    @TableField(value = "DESCR")
    private String descr;

    /**
     * 文件ID
     */
    @TableField(value = "FILE_ID")
    private String fileId;

    @ApiModelProperty("下载地址")
    @TableField(exist = false)
    private String visitPath;

    @ApiModelProperty("原始文件名")
    @TableField(exist = false)
    private String fileName;

    @ApiModelProperty("下载量")
    @TableField("DOWNLOAD_NUM")
    private String downloadNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}