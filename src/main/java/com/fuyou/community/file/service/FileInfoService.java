package com.fuyou.community.file.service;

import com.fuyou.community.common.ResultVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FileInfoService {
    public ResultVo<Object> upload(List<MultipartFile> files, HttpServletRequest request);
}
