package com.fuyou.community.file.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fuyou.community.article.dao.ArticleFileRelMapper;
import com.fuyou.community.article.model.ArticleFileRel;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.exception.ServiceException;
import com.fuyou.community.file.model.FileInfo;
import com.fuyou.community.file.dao.FileInfoMapper;
import com.fuyou.community.file.service.FileInfoService;
import com.fuyou.community.sys.constant.Constant;
import com.fuyou.community.sys.util.CurrentUtil;
import com.fuyou.community.user.dao.UserMapper;
import com.fuyou.community.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileInfoServiceImpl implements FileInfoService {

    private final CurrentUtil currentUtil;
    private final FileInfoMapper fileInfoMapper;
    private final ArticleFileRelMapper fileRelMapper;
    private final UserMapper userMapper;

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
                User userInfo = currentUtil.getLoginUser();
                if (ObjectUtil.isEmpty(userInfo)) {
                    log.error("用户信息获取失败");
                    return ResultVo.fail(5000, "用户信息获取失败");
                }
                String fileId = IdUtil.simpleUUID();
                fileInfo.setId(fileId);
                fileInfo.setUserId(userInfo.getId());
                String originName = file.getOriginalFilename();
                fileInfo.setSaveName(fileId + "." + originName.split("\\.")[1]);
                fileInfo.setVisitPath("http://192.168.199.72:8081/community/upload/" + fileInfo.getSaveName());
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
        return ResultVo.success(2000, "文件上传成功", fileInfo);
    }

    @Override
    public ResultVo<Object> upFile(@RequestParam("files") List<MultipartFile> files, Map<String,String> paramMap) {
        String bizType = paramMap.get("bizType");
        if (StrUtil.isBlank(bizType)) {
            throw new ServiceException(5000, "请检查文件业务条线");
        }
        if (CollUtil.isEmpty(files)){
            throw new ServiceException(5000, "文件列表为空！");
        }
        switch (bizType) {
            case Constant.BizType.ENCL:
//                附件上传
                FileInfo fileInfo = new FileInfo();
                for (MultipartFile file : files) {
                    if (ObjectUtil.isEmpty(file)) {
                        throw new ServiceException(5000, "文件丢失");
                    }
                    String projectPath = CurrentUtil.getProjectPath();
//                    用户独有的目录
                    String savePath = projectPath + "/static/upload/files/" + CurrentUtil.getLoginUser().getId();
                    fileInfo.setUserId(CurrentUtil.getLoginUser().getId());
                    String fileId = IdUtil.simpleUUID();
                    fileInfo.setId(fileId);
                    String originName = file.getOriginalFilename();
                    fileInfo.setSaveName(fileId + "." + originName.split("\\.")[1]);
                    fileInfo.setFileName(originName);
                    fileInfo.setVisitPath("http://192.168.199.72:8081/community/upload/files/" + CurrentUtil.getLoginUser().getId() + "/" + fileInfo.getSaveName());
                    try{
                        File saveFile = new File(savePath, fileInfo.getSaveName());
                        if (!saveFile.getParentFile().exists()){
                            saveFile.getParentFile().mkdirs();
                        }
                        file.transferTo(saveFile);
                    }catch (Exception e){
                        log.error("文件保存异常：{}",e.getMessage());
                        throw new ServiceException(5000,"文件上传失败");
                    }
//                    建立文件和文章的引用关系
                    ArticleFileRel articleFileRel = new ArticleFileRel();
                    articleFileRel.setId(IdUtil.simpleUUID());
                    articleFileRel.setFileId(fileId);
                    if (StrUtil.isNotBlank(paramMap.get("articleId"))){
                        articleFileRel.setArticleId(paramMap.get("articleId"));
                    }
                    fileRelMapper.insert(articleFileRel);
                    int insert = fileInfoMapper.insert(fileInfo);
                    if (insert < 1) {
                        return ResultVo.fail(5000, "文件上传失败");
                    }
                }
                break;
            case Constant.BizType.FILE:
//                文件上传
                break;
            case Constant.BizType.AVATAR:
                if (CollUtil.isEmpty(files)) {
                    log.info("文件信息为空");
                    throw new ServiceException(6000,"文件信息为空");
                }
                FileInfo fileInfoA = new FileInfo();
                for (MultipartFile file : files) {
                    if (ObjectUtil.isEmpty(file)) {
                        throw new ServiceException(5000, "文件丢失");
                    } else {
                        String projectPath = CurrentUtil.getProjectPath();
                        String savePath = projectPath + "/static/upload";
                        User userInfo = CurrentUtil.getLoginUser();
                        if (ObjectUtil.isEmpty(userInfo)) {
                            log.error("用户信息获取失败");
                            return ResultVo.fail(5000, "用户信息获取失败");
                        }
                        String fileId = IdUtil.simpleUUID();
                        fileInfoA.setId(fileId);
                        fileInfoA.setUserId(userInfo.getId());
                        String originName = file.getOriginalFilename();
                        fileInfoA.setSaveName(fileId + "." + originName.split("\\.")[1]);
                        String visitPath = "http://192.168.199.72:8081/community/upload/" + fileInfoA.getSaveName();
                        fileInfoA.setVisitPath(visitPath);
                        fileInfoA.setFileName(originName);
                        int insert = fileInfoMapper.insert(fileInfoA);
                        userMapper.update(null,Wrappers.lambdaUpdate(User.class)
                                .eq(User::getId,userInfo.getId())
                                .set(User::getUserAvatar,visitPath));
                        if (insert < 1) {
                            return ResultVo.fail(5000, "文件上传失败");
                        }
                        try {
                            File saveFile = new File(savePath, fileInfoA.getSaveName());
                            if (!saveFile.getParentFile().exists()) {
                                saveFile.getParentFile().mkdir();
                            }
                            file.transferTo(saveFile);
                        } catch (Exception e) {
                            log.error("文件保存出错：{}", e.getCause());
                            return ResultVo.fail(5000, "文件保存异常");
                        }
                        return ResultVo.success(2000,"头像上传成功",visitPath);
                    }
                }
                break;
        }
        return ResultVo.success(2000, "上传成功");
    }
}
