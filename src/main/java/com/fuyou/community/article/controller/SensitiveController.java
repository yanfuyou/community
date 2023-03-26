package com.fuyou.community.article.controller;

import cn.hutool.core.util.IdUtil;
import com.fuyou.community.article.model.SensitiveWords;
import com.fuyou.community.article.service.SensitiveWordsService;
import com.fuyou.community.common.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sensitive")
@Api(tags = "违禁词")
public class SensitiveController {
    private final SensitiveWordsService sensitiveWordsService;

    @PostMapping("/checkStr")
    @ApiOperation("检查")
    public ResultVo<List<SensitiveWords>> checkStr(@RequestBody String sourceStr){
        return sensitiveWordsService.getWords(sourceStr);
    }

    @PostMapping("/saveWords")
    @ApiOperation("保存")
    public ResultVo<Object> saveWords(List<SensitiveWords> words){
        words.forEach(word -> word.setId(IdUtil.simpleUUID()));
        sensitiveWordsService.saveBatch(words);
        return ResultVo.success(2000,"保存成功");
    }

}
