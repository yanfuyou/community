package com.fuyou.community.hole.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.hole.model.TreeHole;
import com.fuyou.community.hole.model.vo.HoleVo;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import org.apache.ibatis.annotations.Param;

/**
* @author yanfuyou
* @description 针对表【TREE_HOLE】的数据库操作Mapper
* @createDate 2022-12-14 00:23:50
* @Entity com.fuyou.community.hole.model.TreeHole
*/
public interface TreeHoleMapper extends BaseMapper<TreeHole> {
    Page<HoleVo> holePage(Page<HoleVo> page, @Param("hole") PageQueryDto<TreeHole> hole);
}
