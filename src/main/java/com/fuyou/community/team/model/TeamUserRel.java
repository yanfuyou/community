package com.fuyou.community.team.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fuyou.community.sys.model.DelBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("team_user_rel")
@Data
@Accessors(chain = true)
@ApiModel("用户加入的队伍")
public class TeamUserRel extends DelBean implements Serializable {
    @TableId("ID")
    private String id;
    @TableField("USER_ID")
    private String userId;
    @TableField("TEAM_ID")
    private String teamId;
    @TableField("CONTACT")
    private String contact;
    @TableField("INTR")
    private String intr;
    @TableField("NAME")
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = -2128552462004019829L;
}
