package com.fuyou.community.sys.service.impl;

import com.fuyou.community.common.ResultVo;
import com.fuyou.community.sys.dao.SysLabelinfoMapper;
import com.fuyou.community.sys.model.SysLabelinfo;
import com.fuyou.community.sys.service.SysLabelinfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysLabelinfoServiceImpl implements SysLabelinfoService {
    private final SysLabelinfoMapper sysLabelinfoMapper;

    @Override
    public ResultVo saveSysLabel(SysLabelinfo labelinfo) {
        sysLabelinfoMapper.insert(labelinfo);
        return ResultVo.success(2000,"添加成功");
    }
}
