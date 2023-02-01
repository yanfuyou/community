package com.fuyou.community.material.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.material.model.Material;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yanfuyou
 * @description 针对表【material】的数据库操作Mapper
 * @createDate 2023-01-31 21:12:41
 * @Entity com.fuyou.community.material.model.Material
 */
@Mapper
public interface MaterialMapper extends BaseMapper<Material> {

    int downloadCount(String id);

    List<Material> myMaterial(@Param("userName") String userName, @Param("flag") String flag);

    Page<Material> page(@Param("page") Page<Material> page, @Param("material") PageQueryDto<Material> material);
}




