package com.fuyou.community.sys.auth;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import com.fuyou.community.exception.ServiceException;

/**
 * @Author yanfuyou
 * @Description 自定义校验规则
 * @Date 上午2:43 2022/11/22
 */
public class AuthUtil {
    public static void authMethod(){
//        登录校验
        try {
            StpUtil.checkLogin();
        }catch (NotLoginException notLogin){
            throw new ServiceException(notLogin,401,"请登录");
        }
//        权限校验
    }
}
