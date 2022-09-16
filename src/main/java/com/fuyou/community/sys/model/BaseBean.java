package com.fuyou.community.sys.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class BaseBean {
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private String createTime;
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private String updateTime;
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
}
