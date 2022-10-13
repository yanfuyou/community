package com.fuyou.community.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.sys.dao.SysLabelinfoMapper;
import com.fuyou.community.sys.model.SysLabelinfo;
import com.fuyou.community.sys.model.dto.QueryDto;
import com.fuyou.community.sys.service.SysLabelinfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SysLabelinfoServiceImpl implements SysLabelinfoService {
    private final SysLabelinfoMapper sysLabelinfoMapper;

    @Override
    public ResultVo saveSysLabel(SysLabelinfo labelinfo) {
        sysLabelinfoMapper.insert(labelinfo);
        return ResultVo.success(2000,"添加成功");
    }

    @Override
    public ResultVo getSysLabels(QueryDto dto) {
//        需要时改成xml文件添加参数
        List<SysLabelinfo> sysLabelinfos = sysLabelinfoMapper.selectList(Wrappers.lambdaQuery(SysLabelinfo.class)
                .eq(SysLabelinfo::getFlag, dto.getFlag()));
        return ResultVo.success(2000,"获取系统参数成功",sysLabelinfos);
    }
}
