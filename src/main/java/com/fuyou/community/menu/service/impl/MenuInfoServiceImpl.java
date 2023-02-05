package com.fuyou.community.menu.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuyou.community.article.model.CommentInfo;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.menu.dao.MenuInfoMapper;
import com.fuyou.community.menu.model.MenuInfo;
import com.fuyou.community.menu.service.MenuInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuInfoServiceImpl extends ServiceImpl<MenuInfoMapper, MenuInfo> implements MenuInfoService {
    private final MenuInfoMapper menuInfoMapper;

    @Override
    public ResultVo<Object> addMenu(MenuInfo menuInfo) {
        menuInfo.setId(IdUtil.simpleUUID());
        menuInfoMapper.insert(menuInfo);
        return ResultVo.success(2000,"添加成功");
    }

    @Override
    public ResultVo<List<MenuInfo>> tree() {
//        根节点
        List<MenuInfo> all = menuInfoMapper.selectList(Wrappers.lambdaQuery(MenuInfo.class)
                .eq(MenuInfo::getFlag,"0"));
        List<MenuInfo> roots = all.stream().filter((menuInfo -> menuInfo.getParentId().equals("0"))).collect(Collectors.toList());
        roots.stream().forEach(root -> buildTree(root,all));
        return ResultVo.success(2000,"树状菜单获取成功",roots);
    }

    private void buildTree(MenuInfo parent,List<MenuInfo> all){
        List<MenuInfo> children = all.stream().filter(item -> item.getParentId().equals(parent.getId())).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(children)){
            parent.setChildren(children);
            children.stream().forEach(child -> buildTree(child,all));
        }
    }
}
