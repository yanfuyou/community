package com.fuyou.community.file.service;

import com.fuyou.community.common.ResultVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FileInfoService {
    /**
     * 图片上传
     * @param files
     * @param request
     * @return
     */
    ResultVo<Object> upload(List<MultipartFile> files, HttpServletRequest request);

    /**
     * 文件上传
     * @param files 文件
     * @param bizType 业务条线
     * @return
     */
    ResultVo<Object> upFile(List<MultipartFile> files,String bizType);
}
