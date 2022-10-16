package com.fuyou.community.article.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fuyou.community.article.dao.ArticleScoreMapper;
import com.fuyou.community.article.model.ArticleScore;
import com.fuyou.community.article.service.ArticleScoreService;
import com.fuyou.community.common.ResultVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AriticleScoreServiceImpl implements ArticleScoreService {
    private final ArticleScoreMapper scoreMapper;

    @Override
    public ResultVo<List<ArticleScore>> getArtScore(String userId, String artId) {
        List<ArticleScore> articleScores = new ArrayList<>();
        if (StrUtil.isBlank(userId)) {
            articleScores = scoreMapper.selectList(Wrappers.lambdaQuery(ArticleScore.class)
                    .eq(ArticleScore::getArticleId, artId));
        } else {
            articleScores = scoreMapper.selectList(Wrappers.lambdaQuery(ArticleScore.class)
                    .eq(ArticleScore::getUserId, userId)
                    .eq(ArticleScore::getArticleId, artId));
        }
        return ResultVo.success(2000, "获取所有得分成功", articleScores);
    }

    @Override
    public ResultVo addScore(ArticleScore score) {
        ArticleScore old = scoreMapper.selectOne(Wrappers.lambdaQuery(ArticleScore.class)
                .eq(ArticleScore::getArticleId, score.getArticleId())
                .eq(ArticleScore::getUserId, score.getUserId()));
        if (ObjectUtil.isNotEmpty(old)) {
            scoreMapper.update(score, Wrappers.lambdaUpdate(ArticleScore.class)
                    .eq(ArticleScore::getArticleId, score.getArticleId())
                    .eq(ArticleScore::getUserId, score.getUserId()));
        }else{
            scoreMapper.insert(score);
        }
        return ResultVo.success(2000, "评分成功");
    }
}
