package com.fuyou.community.material.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.exception.ServiceException;
import com.fuyou.community.material.model.Material;
import com.fuyou.community.material.service.MaterialService;
import com.fuyou.community.material.dao.MaterialMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yanfuyou
 * @description 针对表【material】的数据库操作Service实现
 * @createDate 2023-01-31 21:12:41
 */
@Service
@RequiredArgsConstructor
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material>
        implements MaterialService {

    private final MaterialMapper materialMapper;

    @Override
    public ResultVo upload(Material material) {
        if (StrUtil.isBlank(material.getFileId())) {
            throw new ServiceException(5000, "建立文件引用关系失败");
        }
        material.setId(IdUtil.simpleUUID());
        return materialMapper.insert(material) > 0 ? ResultVo.success(2000, "资料上传成功") : ResultVo.fail(5000, "上传失败!");
    }

    @Override
    public ResultVo remove(String id) {
        if (StrUtil.isBlank(id)) {
            throw new ServiceException(5000, "请检查参数");
        }
        materialMapper.update(null, Wrappers.lambdaUpdate(Material.class)
                .eq(Material::getId, id)
                .set(Material::getFlag, "1"));
        return ResultVo.success(2000,"删除成功！");
    }

    @Override
    public ResultVo<List<Material>> myMaterial(String userName,String flag) {
        return ResultVo.success(2000,"资料列表获取成功",materialMapper.myMaterial(userName,flag));
    }
}




