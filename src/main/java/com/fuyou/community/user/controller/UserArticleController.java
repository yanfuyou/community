package com.fuyou.community.user.controller;

import com.fuyou.community.common.ResultVo;
import com.fuyou.community.user.model.vo.ReportVo;
import com.fuyou.community.user.service.UserArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/userArt")
@Api(tags = "用户文章相关")
@RequiredArgsConstructor
public class UserArticleController {

    private final UserArticleService userArticleService;
    @GetMapping("/getReport/{userId}")
    @ApiOperation("获取用户文章相关统计")
    public ResultVo<ReportVo> getReportCount(@PathVariable String userId){
        return userArticleService.getReportCount(userId);
    }
}
