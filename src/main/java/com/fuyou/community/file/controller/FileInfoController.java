package com.fuyou.community.file.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.file.controller.model.FileInfo;
import com.fuyou.community.file.service.FileInfoService;
import com.fuyou.community.sys.util.CurrentUtil;
import com.fuyou.community.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/file")
@Slf4j
@RequiredArgsConstructor
public class FileInfoController {
    private final FileInfoService fileInfoService;
    @PostMapping("/upload")
    public ResultVo<Object> upload(@RequestParam("img") List<MultipartFile> files, HttpServletRequest request) {
        return fileInfoService.upload(files,request);
    }
}