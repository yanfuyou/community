package com.fuyou.community.sys.service;

import com.fuyou.community.common.ResultVo;
import com.fuyou.community.sys.model.SysLabelinfo;
import com.fuyou.community.sys.model.dto.QueryDto;


public interface SysLabelinfoService {
    /**
     * 添加系统标签
     * @param labelinfo
     * @return
     */
    ResultVo saveSysLabel(SysLabelinfo labelinfo);

    ResultVo getSysLabels(QueryDto dto);
}
