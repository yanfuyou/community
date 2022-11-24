package com.fuyou.community.role.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuyou.community.role.model.RoleInfo;
import com.fuyou.community.role.service.RoleInfoService;
import com.fuyou.community.role.dao.RoleInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
