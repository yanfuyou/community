package com.fuyou.community.hole.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("树洞")
@Data
public class HoleVo {
    private String id;
    private String msg;
    private String avatar;
    private String time;
    private String barrageStyle;
    private String createTime;
}
