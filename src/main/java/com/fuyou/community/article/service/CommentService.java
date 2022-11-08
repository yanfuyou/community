package com.fuyou.community.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fuyou.community.article.model.CommentInfo;
import com.fuyou.community.article.model.vo.CommentInfoVo;
import com.fuyou.community.common.ResultVo;

import java.util.List;

/**
 * @Author yanfuyou
 * @Description 评论业务层
 * @Date 上午12:42 2022/11/9
 */
public interface CommentService extends IService<CommentInfo> {

    /**
     * @Author yanfuyou
     * @Description 发布评论
     * @Date 上午12:43 2022/11/9
     */
    ResultVo release(CommentInfo commentInfo);

    /**
     * @Author yanfuyou
     * @Description 获取评论列表
     * @Date 上午1:07 2022/11/9
     */
    ResultVo<List<CommentInfo>> list(String id, String bizType);

    /**
     * @Author yanfuyou
     * @Description 获取评论树
     * @Date 上午1:24 2022/11/9
     */
    ResultVo<List<CommentInfoVo>> commentTree(String articleId);
}
