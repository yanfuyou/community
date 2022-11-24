package com.fuyou.community.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fuyou.community.article.model.CommentInfo;
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
    ResultVo<List<CommentInfo>> list(CommentInfo commentInfo);

    /**
     * @Author yanfuyou
     * @Description 获取评论树
     * @Date 上午1:24 2022/11/9
     */
    ResultVo<List<CommentInfo>> commentTree(String articleId);

    /**
     * @Author yanfuyou
     * @Description 删除评论
     * @Date 上午1:28 2022/11/13
     */
    ResultVo delComment(String commentId);

    /**
     * @Author yanfuyou
     * @Description 更新评论信息
     * @Date 上午1:32 2022/11/13
     */
    ResultVo updateComment(CommentInfo commentInfo);
}
