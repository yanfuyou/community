package com.fuyou.community.team.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.fuyou.community.sys.model.DelBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName team_info
 */
@TableName(value ="team_info")
@Data
//开启链式调用
@Accessors(chain = true)
@ApiModel("队伍信息")
public class TeamInfo extends DelBean implements Serializable {
    /**
     * 队伍id
     */
    @TableId(value = "ID")
    private String id;

    /**
     * 队伍名称
     */
    @TableField(value = "NAME")
    private String name;

    /**
     * 赛事链接
     */
    @TableField(value = "URL")
    private String url;

    /**
     * 开始时间
     */
    @TableField(value = "START_TIME")
    private String startTime;

    /**
     * 结束时间
     */
    @TableField(value = "END_TIME")
    private String endTime;

    /**
     * 线上/线下
     */
    @TableField(value = "ONLINE")
    private String online;

    /**
     * 所需人数
     */
    @TableField(value = "NUM")
    private String num;

    /**
     * 队伍简介
     */
    @TableField(value = "DESCR")
    private String descr;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}