package com.fuyou.community.accusation.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.fuyou.community.sys.model.BaseBean;
import lombok.Data;

/**
 * 举报信息
 * @TableName accusation_info
 */
@TableName(value ="accusation_info")
@Data
public class AccusationInfo extends BaseBean implements Serializable {

    @TableId("ID")
    private String id;

    /**
     * 内容id
     */
    @TableField(value = "REL_ID")
    private String relId;

    /**
     * 举报原因
     */
    @TableField(value = "REASON")
    private String reason;

    /**
     * 业务条线
     */
    @TableField(value = "BIZ_TYPE")
    private String bizType;

    /**
     * 状态
     */
    @TableField(value = "ACC_STATUS")
    private String accStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}