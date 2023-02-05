package com.fuyou.community.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.menu.model.MenuInfo;
import com.fuyou.community.role.model.RoleInfo;

import java.awt.*;
import java.util.List;

/**
* @author yanfuyou
* @description 针对表【ROLE_INFO】的数据库操作Service
* @createDate 2022-11-24 23:37:26
*/
public interface RoleInfoService extends IService<RoleInfo> {

    /**
     * @Author yanfuyou
     * @Description 角色列表
     * @Date 下午11:41 2022/11/24
     */
    List<RoleInfo> getRoleList(String userId);

    List<MenuInfo> roleMenu(String roleId);
}
