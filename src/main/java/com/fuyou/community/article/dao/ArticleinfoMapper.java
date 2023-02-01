package com.fuyou.community.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.article.model.ArticleInfo;
import com.fuyou.community.article.model.dto.PageDto;
import com.fuyou.community.article.model.vo.ArticleHotVo;
import com.fuyou.community.article.model.vo.ArticleMiniVo;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleinfoMapper extends BaseMapper<ArticleInfo> {
    /**
     * @Author yanfuyou
     * @Description  获取热文
     * @Date 下午11:18 2022/10/15
     */
    List<ArticleHotVo> getHots(@Param("start") int start,@Param("end") int end);

    /**
     * @Author yanfuyou
     * @Description 文章列表
     * @Date 上午1:07 2022/12/10
     */
    Page<ArticleInfo> list(Page<ArticleInfo> page, @Param("pageDto")PageDto pageDto);

    Page<ArticleMiniVo> miniList(Page<ArticleMiniVo> page,@Param("pageDto") PageDto pageDto);
    /**
     * @Author yanfuyou
     * @Description 阅读量增加
     * @Date 下午5:00 2022/12/29
     */
    int readPlusOne(String id);

    int getRead(String id);

    int insertNewRead(String id);


    Page<ArticleMiniVo> pageSearch(Page<ArticleMiniVo> page, @Param("dto") PageQueryDto<ArticleMiniVo> dto);
}
