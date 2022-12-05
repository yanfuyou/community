package com.fuyou.community.dashboard.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("折线图数据")
public class LineDataVo {
    private List<Integer> userData;
    private List<Integer> CommentData;
    private List<Integer> fileData;
    private List<Integer> articleData;
    private List<String> dateList;
}
