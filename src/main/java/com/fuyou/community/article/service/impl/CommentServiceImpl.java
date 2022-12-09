package com.fuyou.community.article.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuyou.community.article.dao.CommentInfoMapper;
import com.fuyou.community.article.model.CommentInfo;
import com.fuyou.community.article.service.CommentService;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.sys.constant.Constant;
import com.sun.org.apache.bcel.internal.generic.FADD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentInfoMapper, CommentInfo> implements CommentService {
    private final CommentInfoMapper commentInfoMapper;

    @Override
    public ResultVo release(CommentInfo commentInfo) {
        commentInfo.setCommentId(IdUtil.simpleUUID());
        commentInfo.setFlag(0);
        commentInfoMapper.insert(commentInfo);
        return ResultVo.success(2000, "评论发布成功");
    }

    @Override
    public ResultVo<List<CommentInfo>> list(CommentInfo commentInfo) {
        List<CommentInfo> commentInfos = commentInfoMapper.selectList(Wrappers.lambdaQuery(CommentInfo.class)
                .eq(CommentInfo::getArticleId, commentInfo.getArticleId())
                .eq(CommentInfo::getParentId, Constant.Tree.ROOT)
                .eq(CommentInfo::getBizType, commentInfo.getBizType())
                .eq(CommentInfo::getFlag, commentInfo.getFlag()));
        return ResultVo.success(2000, "获取成功", commentInfos);
    }

    @Override
    public ResultVo<List<CommentInfo>> commentTree(String articleId) {
//        当前文章的所有评论
        List<CommentInfo> commentInfos = commentInfoMapper.selectList(Wrappers.lambdaQuery(CommentInfo.class)
                .eq(CommentInfo::getArticleId, articleId)
                .eq(CommentInfo::getFlag, Constant.Base.NOTDELETED));
        List<CommentInfo> roots = commentInfos.stream().filter(c -> c.getParentId().equals(Constant.Tree.ROOT)).collect(Collectors.toList());
        roots.stream().forEach(comment -> {
            buildTreeByParentId(comment,commentInfos);
        });
        return ResultVo.success(2000, "获取评论成功", roots);
    }

    @Override
    public ResultVo delComment(String commentId) {
        int delFlag = commentInfoMapper.update(null, Wrappers.lambdaUpdate(CommentInfo.class)
                .eq(CommentInfo::getCommentId, commentId)
                .set(CommentInfo::getFlag, Constant.Base.DELETED));
        return delFlag > 0 ? ResultVo.success(2000, "删除成功") : ResultVo.fail(5000, "删除失败");
    }

    @Override
    public ResultVo updateComment(CommentInfo commentInfo) {
        int upFlag = commentInfoMapper.updateById(commentInfo);
        return upFlag > 0 ? ResultVo.success(2000, "更新成功") : ResultVo.fail(5000, "更新失败");
    }

    /**
     * @Author yanfuyou
     * @Description 通过父id构建一颗评论树
     * @Date 下午5:39 2022/11/22
     */
    private void buildTreeByParentId(CommentInfo parent, List<CommentInfo> commentInfos){
        List<CommentInfo> children = commentInfos.stream().filter(c -> c.getParentId().equals(parent.getCommentId())).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(children)){
            parent.setChildren(children);
            children.stream().forEach(child -> {
                buildTreeByParentId(child,commentInfos);
            });
        }
    }
}
