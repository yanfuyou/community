package com.fuyou.community.accusation.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.accusation.model.AccusationInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.sys.model.dto.PageQueryDto;

/**
* @author yanfuyou
* @description 针对表【accusation_info(举报信息)】的数据库操作Service
* @createDate 2023-02-11 19:25:17
*/
public interface AccusationInfoService extends IService<AccusationInfo> {
    ResultVo<Object> add(AccusationInfo accusationInfo);

    ResultVo<Page<AccusationInfo>> page(PageQueryDto<AccusationInfo> dto);
}
