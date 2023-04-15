package com.fuyou.community.accusation.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.accusation.model.AccusationInfo;
import com.fuyou.community.accusation.service.AccusationInfoService;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accusation")
@Api(tags = "举报信息")
@RequiredArgsConstructor
public class AccusationController {
    private final AccusationInfoService accusationInfoService;

    @PostMapping("/add")
    @ApiOperation("添加举报")
    public ResultVo<Object> add(@RequestBody AccusationInfo accusationInfo){
        if(StrUtil.isBlank(accusationInfo.getReason())){
            return ResultVo.fail(5000,"理由不能为空");
        }
        return accusationInfoService.add(accusationInfo);
    }

    @PostMapping("/update")
    @ApiOperation("更新举报信息")
    public ResultVo<Object> update(@RequestBody AccusationInfo accusationInfo){
        if(StrUtil.isBlank(accusationInfo.getReason())){
            return ResultVo.fail(5000,"理由不能为空");
        }
        accusationInfoService.updateById(accusationInfo);
        return ResultVo.success(2000,"更新成功");
    }

    @PostMapping("/page")
    @ApiOperation("分页查询")
    public ResultVo<Page<AccusationInfo>> page(@RequestBody PageQueryDto<AccusationInfo> dto){
        return accusationInfoService.page(dto);
    }
}
