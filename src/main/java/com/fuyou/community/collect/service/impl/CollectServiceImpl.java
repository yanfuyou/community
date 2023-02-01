package com.fuyou.community.collect.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuyou.community.article.model.vo.ArticleMiniVo;
import com.fuyou.community.collect.model.Collect;
import com.fuyou.community.collect.service.CollectService;
import com.fuyou.community.collect.dao.CollectMapper;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author yanfuyou
* @description 针对表【COLLECT】的数据库操作Service实现
* @createDate 2022-12-20 23:44:15
*/
@Service
@RequiredArgsConstructor
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect>
    implements CollectService{
    private final CollectMapper collectMapper;
    @Override
    public Page<ArticleMiniVo> myCollect(PageQueryDto<ArticleMiniVo> dto) {
        Page<ArticleMiniVo> page = new Page<>();
        page.setCurrent(dto.getCurrent()).setSize(dto.getSize()).setOrders(dto.getOrders());
//        内容替换操作
        return collectMapper.myCollect(page, dto);
    }
}




