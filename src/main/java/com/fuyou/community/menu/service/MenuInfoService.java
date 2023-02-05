package com.fuyou.community.menu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.menu.model.MenuInfo;

import java.util.List;

/**
* @author yanfuyou
* @description 针对表【menu_info】的数据库操作Service
* @createDate 2023-02-02 22:20:04
*/
public interface MenuInfoService extends IService<MenuInfo> {
    ResultVo<Object> addMenu(MenuInfo menuInfo);

    ResultVo<List<MenuInfo>> tree();
}
