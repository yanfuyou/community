package com.fuyou.community.user.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fuyou.community.article.dao.ArticleinfoMapper;
import com.fuyou.community.article.model.ArticleInfo;
import com.fuyou.community.collect.model.Collect;
import com.fuyou.community.collect.service.CollectService;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.user.dao.ArticleFollowMapper;
import com.fuyou.community.user.dao.ArticleReadMapper;
import com.fuyou.community.user.model.ArticleFollow;
import com.fuyou.community.user.model.vo.ReportVo;
import com.fuyou.community.user.service.UserArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserArticleServiceImpl implements UserArticleService {
    private final ArticleFollowMapper followMapper;

    private final CollectService collectService;
    private final ArticleReadMapper readMapper;
    private final ArticleinfoMapper articleinfoMapper;
    @Override
    public ResultVo<ReportVo> getReportCount(String id) {
        Integer artSum = articleinfoMapper.selectCount(Wrappers.lambdaQuery(ArticleInfo.class)
                .eq(ArticleInfo::getUserId, id));
        Integer followSum = collectService.count(Wrappers.lambdaQuery(Collect.class)
                .eq(Collect::getUserId,id));
        Integer readSum = readMapper.getReadSumByUserId(id);
        if (ObjUtil.isEmpty(readSum)){
            readSum = 0;
        }
        ReportVo reportVo = new ReportVo();
        reportVo.setArtSum(String.valueOf(artSum));
        reportVo.setReadSum(String.valueOf(readSum));
        reportVo.setFollowSum(String.valueOf(followSum));
        return ResultVo.success(2000,"获取统计量成功",reportVo);
    }
}
