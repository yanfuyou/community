package com.fuyou.community.article.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fuyou.community.article.dao.ArticleFileRelMapper;
import com.fuyou.community.article.dao.ArticleinfoMapper;
import com.fuyou.community.article.model.ArticleFileRel;
import com.fuyou.community.article.model.ArticleInfo;
import com.fuyou.community.article.service.ArticleService;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.exception.ServiceException;
import com.fuyou.community.sys.constant.Constant;
import com.fuyou.community.sys.util.CurrentUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    private final ArticleFileRelMapper articleFileRelMapper;

    private final ArticleinfoMapper articleinfoMapper;

    @Override
    public ResultVo release(ArticleInfo articleInfo) {
        String id = articleInfo.getId();
//        附件判断
        ArticleFileRel articleFileRel = articleFileRelMapper.selectOne(Wrappers.lambdaQuery(ArticleFileRel.class)
                .eq(ArticleFileRel::getArticleId,id));
        if (ObjectUtil.isNotEmpty(articleFileRel)){
            articleInfo.setEnclosure(Constant.ArticleEnc.HAS_ENC);
        }else {
            articleInfo.setEnclosure(Constant.ArticleEnc.NO_ENC);
        }
//        替换文件内容
        String projectPath = System.getProperty("user.dir");
        String savePath = projectPath + "/static/articles";
        File file = new File(savePath, articleInfo.getId() + ".md");
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdir();
        }
        try{
            FileOutputStream out = new FileOutputStream(file);
            out.write(articleInfo.getArticleContent().getBytes("UTF-8"));
            out.flush();
            out.close();
            articleInfo.setArticleContent(savePath + articleInfo.getId() + ".md");
        }catch (Exception e){
            log.error("保存文章内容异常：{}",e.getMessage());
            throw new ServiceException(500,"保存文章内容异常",e);
        }
        articleInfo.setUserId(CurrentUtil.getLoginUser().getId());
        articleinfoMapper.insert(articleInfo);
        return ResultVo.success(2000,"发布成功");
    }
}
