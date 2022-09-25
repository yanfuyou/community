package com.fuyou.community.article.service;

import com.fuyou.community.article.model.ArticleInfo;
import com.fuyou.community.common.ResultVo;

public interface ArticleService {
    ResultVo release(ArticleInfo articleInfo);
}
