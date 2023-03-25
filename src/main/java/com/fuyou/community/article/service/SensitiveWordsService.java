package com.fuyou.community.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fuyou.community.article.model.SensitiveWords;
import com.fuyou.community.common.ResultVo;

import java.util.List;

public interface SensitiveWordsService extends IService<SensitiveWords> {

    /**
     * 查询文本是否包含违禁词
     * @param sourceStr 源文本
     * @return 违禁词列表
     */
    ResultVo<List<SensitiveWords>> getWords(String sourceStr);
}
