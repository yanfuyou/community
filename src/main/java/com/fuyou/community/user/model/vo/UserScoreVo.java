package com.fuyou.community.user.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("用户得分")
public class UserScoreVo {
    private int all;
    private int thisMonth;
}
