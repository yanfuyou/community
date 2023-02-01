package com.fuyou.community.collect.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.article.model.vo.ArticleMiniVo;
import com.fuyou.community.collect.model.Collect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fuyou.community.sys.model.dto.PageQueryDto;


/**
 * @author yanfuyou
 * @description 针对表【COLLECT】的数据库操作Service
 * @createDate 2022-12-20 23:44:15
 */
public interface CollectService extends IService<Collect> {
    Page<ArticleMiniVo> myCollect(PageQueryDto<ArticleMiniVo> dto);
}
