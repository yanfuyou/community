package com.fuyou.community.user.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("统计结果")
public class ReportVo {
    private String artSum;

    private String readSum;

    private String rank;

    private String maSum;

    private String followSum;
}
