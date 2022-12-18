package com.fuyou.community.sys.handler;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.fuyou.community.sys.util.CurrentUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BaseBeanHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", DateUtil.format(new Date(), "yyyyMMddhhmmss"), metaObject);
        if (ObjectUtil.isNotEmpty(CurrentUtil.getLoginUser())){
            this.setFieldValByName("createBy", CurrentUtil.getLoginUser().getUserName(), metaObject);
            this.setFieldValByName("updateBy", CurrentUtil.getLoginUser().getUserName(), metaObject);
        }
        this.setFieldValByName("updateTime", DateUtil.format(new Date(), "yyyyMMddhhmmss"), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", DateUtil.format(new Date(), "yyyyMMddhhmmss"), metaObject);
        if (ObjectUtil.isNotEmpty(CurrentUtil.getLoginUser())) {
            this.setFieldValByName("updateBy", CurrentUtil.getLoginUser().getUserName(), metaObject);
        }
    }
}
