package com.fuyou.community.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fuyou.community.article.model.ArticleScore;

public interface ArticleScoreMapper extends BaseMapper<ArticleScore> {
    Integer getSumScoreByUserId(String id);
    Integer getScoreSum(String ArticleId);
}
