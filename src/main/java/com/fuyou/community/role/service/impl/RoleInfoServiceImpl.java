package com.fuyou.community.role.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.exception.ServiceException;
import com.fuyou.community.menu.model.MenuInfo;
import com.fuyou.community.role.dao.RoleInfoMapper;
import com.fuyou.community.role.model.RoleInfo;
import com.fuyou.community.role.service.RoleInfoService;
import com.fuyou.community.sys.util.EmailUtil;
import com.fuyou.community.user.dao.UserMapper;
import com.fuyou.community.user.model.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    private final UserMapper userMapper;
    private final EmailUtil emailUtil;

    @Value("${community.adminAddress}")
    private String adminAddress;

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
        if(CollUtil.isNotEmpty(menuIds)){
            roleInfoMapper.addRoleMenu(roleId,menuIds);
        }
        return ResultVo.success(2000,"设置成功");
    }

    @Override
    public ResultVo<Object> addUserRoleRel(String userId, String roleId) {
        roleInfoMapper.delUserRoleRel(userId,roleId);
        if(!"f695ab4049294c818ab806a1ffa0af93".equals(roleId)){
            roleInfoMapper.addRel(userId,roleId);
        }
        return ResultVo.success(2000,"添加成功");
    }

    @Override
    public ResultVo<Object> beAdmin(String userId) {
        Integer existed = roleInfoMapper.existed(userId);
        if (ObjectUtil.isNotEmpty(existed) && existed > 0){
            throw new ServiceException(5000,"该用户已在管理员行列");
        }
        User user = userMapper.selectById(userId);
        //发送邮件通知
        if (ObjectUtil.isNotEmpty(user) && StrUtil.isNotBlank(user.getUserEmail())){
            String topic = "你已成为“醒狮”后台管理员";
            String content = "恭喜你被评选进入“醒狮”管理员行列\n你可已通过以下链接：\n" + adminAddress
                    + "\n以及你已有的账户密码，进入后台。" +
                    "\n如无法进行更多的管理操作，请等待平台为你分配角色。";
            emailUtil.sendMail(user.getUserEmail(),topic,content,null);
        }
        roleInfoMapper.beAdmin(userId);
        return ResultVo.success(2000,"设置成功");
    }
}
