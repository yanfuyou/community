package com.fuyou.community.hole.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.hole.model.TreeHole;
import com.fuyou.community.hole.model.vo.HoleVo;
import com.fuyou.community.sys.model.dto.PageQueryDto;

/**
* @author yanfuyou
* @description 针对表【TREE_HOLE】的数据库操作Service
* @createDate 2022-12-14 00:23:50
*/
public interface TreeHoleService extends IService<TreeHole> {
    /**
     * @Author yanfuyou
     * @Description 分页查询树洞列表
     * @Date 下午9:00 2022/12/18
     */
    ResultVo<Page<HoleVo>> holePage(PageQueryDto<TreeHole> hole);
}
