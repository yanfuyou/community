package com.fuyou.community.collect.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.article.model.vo.ArticleMiniVo;
import com.fuyou.community.collect.model.Collect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import org.apache.ibatis.annotations.Param;

/**
* @author yanfuyou
* @description 针对表【COLLECT】的数据库操作Mapper
* @createDate 2022-12-20 23:44:15
* @Entity com.fuyou.community.collect.model.Collect
*/
public interface CollectMapper extends BaseMapper<Collect> {
    Page<ArticleMiniVo> myCollect(Page<ArticleMiniVo> page, @Param("article")PageQueryDto<ArticleMiniVo> article);
}




