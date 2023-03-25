package com.fuyou.community.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fuyou.community.article.model.SensitiveWords;

import java.util.List;

public interface SensitiveWordsMapper extends BaseMapper<SensitiveWords> {
    List<SensitiveWords> getWords(String sourceStr);
}
