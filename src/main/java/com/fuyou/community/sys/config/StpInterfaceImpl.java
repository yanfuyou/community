package com.fuyou.community.sys.config;

import cn.dev33.satoken.stp.StpInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author yanfuyou
 * @Description sa-token权限拦截实现类
 * @Date 上午2:57 2022/11/21
 */
@Component
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object id, String type) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object id, String type) {
        return null;
    }
}
