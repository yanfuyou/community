package com.fuyou.community.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fuyou.community.article.model.ArticleFileRel;
import com.fuyou.community.article.model.vo.EnclVo;

public interface ArticleFileRelMapper extends BaseMapper<ArticleFileRel> {
    EnclVo getEnclInfo(String id);
}
