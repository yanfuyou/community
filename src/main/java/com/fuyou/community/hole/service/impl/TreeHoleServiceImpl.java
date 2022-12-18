package com.fuyou.community.hole.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.hole.model.TreeHole;
import com.fuyou.community.hole.model.vo.HoleVo;
import com.fuyou.community.hole.service.TreeHoleService;
import com.fuyou.community.hole.dao.TreeHoleMapper;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yanfuyou
 * @description 针对表【TREE_HOLE】的数据库操作Service实现
 * @createDate 2022-12-14 00:23:50
 */
@Service
@RequiredArgsConstructor
public class TreeHoleServiceImpl extends ServiceImpl<TreeHoleMapper, TreeHole>
        implements TreeHoleService {
    private final TreeHoleMapper treeHoleMapper;
    @Override
    public ResultVo<Page<HoleVo>> holePage(PageQueryDto<TreeHole> hole) {
        Page<HoleVo> page = new Page<>();
        page.setCurrent(hole.getCurrent());
        page.setSize(hole.getSize());
        page.setOrders(hole.getOrders());
        Page<HoleVo> holeVoPage = treeHoleMapper.holePage(page, hole);
        List<HoleVo> records = holeVoPage.getRecords();
        records.forEach(h -> {
//            填充屏幕显示时间
            h.setTime(String.valueOf(RandomUtil.randomInt(3,8)));
        });
        holeVoPage.setRecords(records);
        return ResultVo.success(2000,"获取成功",holeVoPage);
    }
}
