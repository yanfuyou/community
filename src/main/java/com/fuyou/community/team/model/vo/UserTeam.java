package com.fuyou.community.team.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserTeam {
    private String relId;
    private String teamId;
    private String name;
    private String endTime;
    private String descr;
    private String num;
    private String enNum;
    private String online;
}
