package com.fuyou.community.user.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fuyou.community.sys.model.BaseBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@TableName("user_eduinfo")
@ApiModel("用户教育信息")
@Data
public class UserEduInfo extends BaseBean implements Serializable {
    private static final long serialVersionUID = -7236237761433611488L;
    @TableField("ID")
    private String id;

    @TableField("USER_ID")
    private String userId;

    @TableField("SCH_NAME")
    private String schName;

    @TableField("MAJOR")
    private String major;

    @TableField("GRA_TIME")
    private String graTime;

    @TableField("EDU_BAK")
    private String eduBak;
}
