package com.fuyou.community.user.service;

import com.fuyou.community.common.ResultVo;
import com.fuyou.community.user.model.vo.ReportVo;


public interface UserArticleService {
    ResultVo<ReportVo> getReportCount(String id);
}
