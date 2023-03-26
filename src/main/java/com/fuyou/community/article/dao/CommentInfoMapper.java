package com.fuyou.community.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.article.model.CommentInfo;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentInfoMapper extends BaseMapper<CommentInfo> {
    Page<CommentInfo> pageList(@Param("page") Page<CommentInfo> page, @Param("dto") PageQueryDto<CommentInfo> dto);

    List<CommentInfo> articleComment(String articleId);
}
