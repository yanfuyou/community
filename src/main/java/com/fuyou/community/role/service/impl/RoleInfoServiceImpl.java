package com.fuyou.community.role.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.menu.model.MenuInfo;
import com.fuyou.community.role.model.RoleInfo;
import com.fuyou.community.role.service.RoleInfoService;
import com.fuyou.community.role.dao.RoleInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

/**
 * @author yanfuyou
 * @description 针对表【ROLE_INFO】的数据库操作Service实现
 * @createDate 2022-11-24 23:37:26
 */
@Service
@RequiredArgsConstructor
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo>
        implements RoleInfoService {
    private final RoleInfoMapper roleInfoMapper;
    @Override
    public List<RoleInfo> getRoleList(String userId) {
        return roleInfoMapper.getRoleList(userId);
    }

    @Override
    public List<MenuInfo> roleMenu(String roleId) {
        return roleInfoMapper.roleMenu(roleId);
    }

    @Override
    public ResultVo<Object> add(RoleInfo roleInfo) {
        roleInfo.setRoleId(IdUtil.simpleUUID());
        roleInfoMapper.insert(roleInfo);
        return ResultVo.success(2000,"添加成功");
    }

    @Override
    public ResultVo<Object> addRoleMenu(String roleId,List<String> menuIds) {
//        删除旧的引用数据
        roleInfoMapper.delRel(roleId);
//        重新添加引用
        roleInfoMapper.addRoleMenu(roleId,menuIds);
        return ResultVo.success(2000,"设置成功");
    }
}
