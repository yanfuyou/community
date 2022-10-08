package com.fuyou.community.file.controller;

import com.fuyou.community.common.ResultVo;
import com.fuyou.community.file.service.FileInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/file")
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class FileInfoController {
    private final FileInfoService fileInfoService;
    @PostMapping("/upload")
    public ResultVo<Object> upload(@RequestParam("img") List<MultipartFile> files, HttpServletRequest request) {
        return fileInfoService.upload(files,request);
    }

    @PostMapping("/upFile")
    public ResultVo<Object> upFile(@RequestParam("files") List<MultipartFile> files, String bizType){
        bizType = "file";
        return fileInfoService.upFile(files,bizType);
    }
}
