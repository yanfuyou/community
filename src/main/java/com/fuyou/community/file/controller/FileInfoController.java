package com.fuyou.community.file.controller;

import cn.hutool.core.util.StrUtil;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.file.service.FileInfoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
@Api(tags = "文件相关")
public class FileInfoController {
    private final FileInfoService fileInfoService;
    @PostMapping("/upload")
    public ResultVo<Object> upload(@RequestParam("img") List<MultipartFile> files, HttpServletRequest request) {
        return fileInfoService.upload(files,request);
    }

    @PostMapping("/upFile")
    public ResultVo<Object> upFile(@RequestParam("files") List<MultipartFile> files,String bizType,String articleId){
        Map<String,String> paramMap = new HashMap<>(2);
        if (StrUtil.isNotBlank(bizType)){
            paramMap.put("bizType",bizType);
        }
        if (StrUtil.isNotBlank(articleId)){
            paramMap.put("articleId",articleId);
        }
        return fileInfoService.upFile(files,paramMap);
    }
}
