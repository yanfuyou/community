package com.fuyou.community.sys.controller;

import com.fuyou.community.common.ResultVo;
import com.fuyou.community.sys.model.SysLabelinfo;
import com.fuyou.community.sys.service.SysLabelinfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
@Api(tags = "系统标签")
@RequiredArgsConstructor
public class SysLabelinfoController {
    private final SysLabelinfoService labelinfoService;

    @PostMapping("/label/save")
    @ApiOperation("/添加系统标签")
    public ResultVo saveSysLabel(@RequestBody SysLabelinfo labelinfo){
        return labelinfoService.saveSysLabel(labelinfo);
    }

}
