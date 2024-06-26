package com.fuyou.community.file.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.file.model.FileInfo;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface FileInfoService extends IService<FileInfo> {
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
     * @param paramMap 参数
     * @return
     */
    ResultVo<Object> upFile(List<MultipartFile> files, Map<String,String> paramMap);

    /**
     * 视频文件列表
     * @param dto
     * @return
     */
    ResultVo<Page<FileInfo>> videoList(PageQueryDto<FileInfo> dto);
}
