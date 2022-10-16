package com.fuyou.community.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fuyou.community.user.model.ArticleRead;

public interface ArticleReadMapper extends BaseMapper<ArticleRead> {
    Integer getReadSumByUserId(String id);
}
