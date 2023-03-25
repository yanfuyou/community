package com.fuyou.community.user.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fuyou.community.sys.model.BaseBean;
import lombok.Data;

@TableName("user_workinfo")
@Data
public class UserWorkinfo extends BaseBean {
    @TableId("ID")
    private String id;
    @TableField("USER_ID")
    private String userId;
    @TableField("COM_NAME")
    private String comName;
    @TableField("POS_NAME")
    private String posName;
    @TableField("ADDRESS")
    private String address;
    @TableField("IN_TIME")
    private String inTime;
}
