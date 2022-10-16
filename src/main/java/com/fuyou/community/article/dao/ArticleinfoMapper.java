package com.fuyou.community.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fuyou.community.article.model.ArticleInfo;
import com.fuyou.community.article.model.vo.ArticleHotVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleinfoMapper extends BaseMapper<ArticleInfo> {
    /**
     * @Author yanfuyou
     * @Description  获取热文
     * @Date 下午11:18 2022/10/15
     */
    List<ArticleHotVo> getHots(@Param("start") int start,@Param("end") int end);
}
