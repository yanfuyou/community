package com.fuyou.community.article.service;

import com.fuyou.community.article.model.ArticleScore;
import com.fuyou.community.common.ResultVo;

import java.util.List;

public interface ArticleScoreService {
    /**
     * @Author yanfuyou
     * @Description 获取文章得分
     * @Date 下午5:41 2022/10/16
     */
    ResultVo<List<ArticleScore>> getArtScore(String userId,String artId);

    /**
     * @Author yanfuyou
     * @Description 给文章评分
     * @Date 下午5:42 2022/10/16
     */
    ResultVo addScore(ArticleScore score);
}
