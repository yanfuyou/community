package com.fuyou.community.article.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fuyou.community.article.model.ArticleCover;
import com.fuyou.community.article.model.ArticleInfo;
import com.fuyou.community.article.model.dto.PageDto;
import com.fuyou.community.article.model.vo.ArticleHotVo;
import com.fuyou.community.article.model.vo.ArticleMiniVo;
import com.fuyou.community.article.model.vo.EnclVo;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.sys.model.dto.PageQueryDto;

import java.io.IOException;
import java.util.List;

public interface ArticleService extends IService<ArticleInfo> {
    /**
     * @Author yanfuyou
     * @Description 发布文章
     * @Date 下午10:27 2022/10/15
     */
    ResultVo release(ArticleInfo articleInfo);

    /**
     * @Author yanfuyou
     * @Description 删除文章
     * @Date 下午8:53 2023/2/1
     */
    ResultVo remove(String id);

    /**
     * @Author yanfuyou
     * @Description 设置封面
     * @Date 下午10:29 2022/10/15
     */
    void addCover(ArticleCover articleCover);
    /**
     * @Author yanfuyou
     * @Description 获取热文
     * @Date 下午11:17 2022/10/15
     */
    ResultVo<List<ArticleHotVo>> getHots(String start, String end);

    /**
     * @Author yanfuyou
     * @Description 根据id获取文章信息
     * @Date 下午2:18 2022/10/16
     */
    ResultVo<ArticleInfo> getArticleInfo(String id) throws IOException;

    /**
     * @Author yanfuyou
     * @Description 获取文件附件信息
     * @Date 下午4:12 2022/10/16
     */
    ResultVo<EnclVo> getEnclInfo(String id);
    /**
     * @Author yanfuyou
     * @Description 列表查询
     * @Date 下午11:47 2022/11/27
     */
    ResultVo<Page<ArticleInfo>> list(PageDto pageDto);

    /**
     * @Author yanfuyou
     * @Description 获取mini视图列表
     * @Date 上午1:06 2022/12/10
     */
    ResultVo<Page<ArticleMiniVo>> miniList(PageDto pageDto);

    /**
     * @Author yanfuyou
     * @Description 文章阅读量加一
     * @Date 下午4:58 2022/12/29
     */
    ResultVo<Object> readPlusOne(String id);

    ResultVo<Page<ArticleMiniVo>> pageSearch(PageQueryDto<ArticleMiniVo> dto);
}
