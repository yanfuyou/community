package com.fuyou.community.file.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.file.controller.model.FileInfo;
import com.fuyou.community.file.dao.FileInfoMapper;
import com.fuyou.community.sys.util.CurrentUtil;
import com.fuyou.community.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileInfoServiceImpl implements FileInfoService {

    private final CurrentUtil currentUtil;
    private final FileInfoMapper fileInfoMapper;


    @Override
    public ResultVo<Object> upload(List<MultipartFile> files, HttpServletRequest request) {
        if (CollUtil.isEmpty(files)) {
            log.info("文件信息为空");
            return ResultVo.fail(5000, "文件信息为空");
        }
        FileInfo fileInfo = new FileInfo();
        for (MultipartFile file : files) {
            if (ObjectUtil.isEmpty(file)) {
                return ResultVo.fail(5000, "文件丢失");
            } else {
//                文件保存流程
                String projectPath = CurrentUtil.getProjectPath();
                String savePath = projectPath + "/static/upload";
                User userInfo = currentUtil.getUserInfo(request);
                if (ObjectUtil.isEmpty(userInfo)) {
                    log.error("用户信息获取失败");
                    return ResultVo.fail(5000, "用户信息获取失败");
                }
                String fileId = IdUtil.simpleUUID();
                fileInfo.setId(fileId);
                fileInfo.setUserId(userInfo.getId());
                String originName = file.getOriginalFilename();
                fileInfo.setSaveName(fileId + "." + originName.split("\\.")[1]);
                fileInfo.setVisitPath("http://192.168.2.228:8081/community/upload/" + fileInfo.getSaveName());
                fileInfo.setFileName(originName);
                int insert = fileInfoMapper.insert(fileInfo);
                if (insert < 1) {
                    return ResultVo.fail(5000, "文件上传失败");
                }
                try {
                    File saveFile = new File(savePath, fileInfo.getSaveName());
                    if (!saveFile.getParentFile().exists()) {
                        saveFile.getParentFile().mkdir();
                    }
                    file.transferTo(saveFile);
                } catch (Exception e) {
                    log.error("文件保存出错：{}", e.getCause());
                    return ResultVo.fail(5000, "文件保存异常");
                }
            }
        }
        return ResultVo.success(2000, "文件上传成功",fileInfo);
    }
}
