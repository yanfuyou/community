package com.fuyou.community.article.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuyou.community.article.dao.ArticleCoverMapper;
import com.fuyou.community.article.dao.ArticleFileRelMapper;
import com.fuyou.community.article.dao.ArticleinfoMapper;
import com.fuyou.community.article.model.ArticleCover;
import com.fuyou.community.article.model.ArticleFileRel;
import com.fuyou.community.article.model.ArticleInfo;
import com.fuyou.community.article.model.CommentInfo;
import com.fuyou.community.article.model.dto.PageDto;
import com.fuyou.community.article.model.vo.ArticleHotVo;
import com.fuyou.community.article.model.vo.ArticleMiniVo;
import com.fuyou.community.article.model.vo.EnclVo;
import com.fuyou.community.article.service.ArticleScoreService;
import com.fuyou.community.article.service.ArticleService;
import com.fuyou.community.article.service.CommentService;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.exception.ServiceException;
import com.fuyou.community.sys.constant.Constant;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import com.fuyou.community.sys.util.ArticleContentUtil;
import com.fuyou.community.sys.util.CurrentUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleinfoMapper,ArticleInfo> implements ArticleService {
    private final ArticleFileRelMapper articleFileRelMapper;
    private final ArticleinfoMapper articleinfoMapper;
    private final ArticleCoverMapper coverMapper;
    private final ArticleScoreService scoreService;
    private final CommentService commentService;

    @Override
    public ResultVo release(ArticleInfo articleInfo) {
        String id = articleInfo.getId();
//        附件判断
//        ArticleFileRel articleFileRel = articleFileRelMapper.selectOne(Wrappers.lambdaQuery(ArticleFileRel.class)
//                .eq(ArticleFileRel::getArticleId,id));
//        if (ObjectUtil.isNotEmpty(articleFileRel)){
//            articleInfo.setEnclosure(Constant.ArticleEnc.HAS_ENC);
//        }else {
//            articleInfo.setEnclosure(Constant.ArticleEnc.NO_ENC);
//        }
//        替换文件内容
        /**
         * 暂时去掉了使用文件存储内容信息的需求
        String projectPath = System.getProperty("user.dir");
        String savePath = projectPath + "/static/articles/";
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
            throw new ServiceException(5000,"保存文章内容异常",e);
        }
         */

        String userId = CurrentUtil.getLoginUser().getId();
        if (StrUtil.isBlank(userId)){
            throw new ServiceException(401,"用户登录信息丢失");
        }
        articleInfo.setUserId(userId);
        articleinfoMapper.insert(articleInfo);
        return ResultVo.success(2000,"发布成功");
    }

    @Override
    public ResultVo remove(String id) {
        articleinfoMapper.update(null,Wrappers.lambdaUpdate(ArticleInfo.class)
                .eq(ArticleInfo::getId,id)
                .set(ArticleInfo::getFlag,"1"));
        return ResultVo.success(2000,"删除成功");
    }

    @Override
    public void addCover(ArticleCover articleCover) {
        ArticleCover oldCover = coverMapper.selectOne(Wrappers.lambdaQuery(ArticleCover.class)
                .eq(ArticleCover::getArticleId, articleCover.getArticleId()));
        if (ObjectUtil.isNotEmpty(oldCover)){
            coverMapper.update(articleCover,Wrappers.lambdaUpdate(ArticleCover.class)
                    .eq(ArticleCover::getArticleId,articleCover.getArticleId()));
        }else {
            coverMapper.insert(articleCover);
        }
    }

    @Override
    public ResultVo<List<ArticleHotVo>> getHots(String start, String end) {
        List<ArticleHotVo> hots = articleinfoMapper.getHots(Integer.parseInt(start), Integer.parseInt(end));
        hots.stream().forEach(art -> {
//            替换内容
//            String contentPath = art.getArticleContent();
//            File file = new File(contentPath);
//            if (!file.exists()){
//                throw new ServiceException(5000,"文件内容丢失");
//            }
//            InputStream in = null;
//            try{
//                in = new FileInputStream(file);
//                int len = 0;
//                byte[] swap = new byte[1024];
//                StringBuffer contentBuffer = new StringBuffer("");
//                while ((len = in.read(swap)) != -1){
//                    contentBuffer.append(new String(swap,0,len));
//                    if (contentBuffer.toString().length() >= 20){
//                        break;
//                    }
//                }
//                art.setArticleContent(contentBuffer.substring(0,20));
//                contentBuffer.setLength(0);
//            }catch (Exception e){
//                log.error(e.getMessage());
//                throw new ServiceException(5000,"获取文件内容失败");
//            }finally {
//                if (ObjectUtil.isNotNull(in)){
//                    try {
//                        in.close();
//                    } catch (IOException e) {
//                        throw new ServiceException(5000,"文件流关闭异常");
//                    }
//                }
//            }
        });
        return ResultVo.success(2000,"获取热文成功",hots);
    }

    @Override
    public ResultVo<ArticleInfo> getArticleInfo(String id) throws IOException {
        ArticleInfo articleInfo = articleinfoMapper.selectById(id);
//        覆盖文件内容
//        if (ObjectUtil.isNotEmpty(articleInfo)){
//            String contentPath = articleInfo.getArticleContent();
//            File file = new File(contentPath);
//            if (!file.exists()){
//                throw new ServiceException(5000,"文件内容丢失");
//            }
//            InputStream in = null;
//            try{
//                in = new FileInputStream(file);
//                int len = 0;
//                byte[] swap = new byte[1024];
//                StringBuffer contentBuffer = new StringBuffer("");
//                while ((len = in.read(swap)) != -1){
//                    contentBuffer.append(new String(swap,0,len));
//                }
//                articleInfo.setArticleContent(contentBuffer.toString());
//                contentBuffer.setLength(0);
//            }catch (Exception e){
//                log.error(e.getMessage());
//                throw new ServiceException(5000,"获取文件内容失败");
//            }finally {
//                if (ObjectUtil.isNotNull(in)){
//                    in.close();
//                }
//            }
//        }
        return ResultVo.success(2000,"获取文章信息成功",articleInfo);
    }

    @Override
    public ResultVo<EnclVo> getEnclInfo(String id) {
        return ResultVo.success(2000,"获取附件成功",articleFileRelMapper.getEnclInfo(id));
    }

    @Override
    public ResultVo<Page<ArticleInfo>> list(PageDto pageDto) {
        Page<ArticleInfo> articleInfoPage = new Page<ArticleInfo>();
        articleInfoPage.setOptimizeCountSql(true);
        articleInfoPage.setCurrent(pageDto.getCurrent());
        articleInfoPage.setSize(pageDto.getSize());
        articleInfoPage.setOrders(pageDto.getOrders());
        Page<ArticleInfo> list = articleinfoMapper.list(articleInfoPage,pageDto);
        return ResultVo.success(2000,"查询成功",list);
    }

    @Override
    public ResultVo<Page<ArticleMiniVo>> miniList(PageDto pageDto) {
        Page<ArticleMiniVo> page = new Page<>();
        page.setSize(pageDto.getSize());
        page.setCurrent(pageDto.getCurrent());
        page.setOrders(pageDto.getOrders());
        Page<ArticleMiniVo> list = articleinfoMapper.miniList(page, pageDto);
        List<ArticleMiniVo> records = list.getRecords();
        records.forEach(article -> {
//            填充获得分数和评论数
            int scoreSum = scoreService.getScoreSum(article.getId());
            int commentSum = commentService.count(Wrappers.lambdaQuery(CommentInfo.class)
                    .eq(CommentInfo::getArticleId, article.getId()));
            article.setCommCount(commentSum);
            article.setScoreCount(scoreSum);
//            替换内容
//            String content = ArticleContentUtil.getContent(article.getArticleContent());
//            article.setArticleContent(content);
        });
        return ResultVo.success(2000,"查询自己的文章列表成功",list);
    }

    @Override
    public ResultVo<Object> readPlusOne(String id) {
        int read = articleinfoMapper.getRead(id);
        if (read > 0){
            articleinfoMapper.readPlusOne(id);
        }else {
            articleinfoMapper.insertNewRead(id);
        }
        return ResultVo.success(2000,"成功");
    }

    @Override
    public ResultVo<Page<ArticleMiniVo>> pageSearch(PageQueryDto<ArticleMiniVo> dto) {
        Page<ArticleMiniVo> page = new Page<>();
        page.setCurrent(dto.getCurrent()).setSize(dto.getSize()).setOrders(dto.getOrders());
        return ResultVo.success(2000,"成功",articleinfoMapper.pageSearch(page,dto));
    }
}
