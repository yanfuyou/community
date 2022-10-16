package com.fuyou.community.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fuyou.community.sys.model.SysLabelinfo;
import com.fuyou.community.sys.model.dto.QueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLabelinfoMapper extends BaseMapper<SysLabelinfo> {
    List<SysLabelinfo> getLabels(@Param("dto") QueryDto dto);
}
