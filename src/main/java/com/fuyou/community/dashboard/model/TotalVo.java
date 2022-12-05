package com.fuyou.community.dashboard.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("数量统计")
public class TotalVo {
    private int userTotal;
    private int commentTotal;
    private int fileTotal;
    private int articleTotal;
}
