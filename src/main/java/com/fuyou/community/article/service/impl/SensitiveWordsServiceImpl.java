package com.fuyou.community.article.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuyou.community.article.dao.SensitiveWordsMapper;
import com.fuyou.community.article.model.SensitiveWords;
import com.fuyou.community.article.service.SensitiveWordsService;
import com.fuyou.community.common.ResultVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensitiveWordsServiceImpl extends ServiceImpl<SensitiveWordsMapper, SensitiveWords> implements SensitiveWordsService {

    private final SensitiveWordsMapper sensitiveWordsMapper;

    @Override
    public ResultVo<List<SensitiveWords>> getWords(String sourceStr) {
        ResultVo<List<SensitiveWords>> resultVo = new ResultVo<>();
        List<SensitiveWords> words = sensitiveWordsMapper.getWords(sourceStr);
        return ResultVo.success(2000,"查询成功",words);
    }
}
