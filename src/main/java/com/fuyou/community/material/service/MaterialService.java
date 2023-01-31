package com.fuyou.community.material.service;

import com.fuyou.community.common.ResultVo;
import com.fuyou.community.material.model.Material;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author yanfuyou
* @description 针对表【material】的数据库操作Service
* @createDate 2023-01-31 21:12:41
*/
public interface MaterialService extends IService<Material> {

    ResultVo upload(Material material);

    ResultVo remove(String id);

    ResultVo<List<Material>> myMaterial(String userName,String flag);
}
