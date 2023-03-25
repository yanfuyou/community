package com.fuyou.community.user.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fuyou.community.sys.model.BaseBean;
import lombok.Data;


@TableName("user_labelinfo")
@Data
public class UserLabelinfo extends BaseBean {
    @TableId("ID")
    private String id;
    @TableField("USER_ID")
    private String userId;
    @TableField("LABEL_ID")
    private String labelId;
}
