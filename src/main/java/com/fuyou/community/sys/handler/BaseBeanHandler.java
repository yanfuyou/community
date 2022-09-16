package com.fuyou.community.sys.handler;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BaseBeanHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", DateUtil.format(new Date(), "yyyyMMddhhmmss"), metaObject);
        this.setFieldValByName("createBy", "admin", metaObject);
        this.setFieldValByName("updateTime", DateUtil.format(new Date(), "yyyyMMddhhmmss"), metaObject);
        this.setFieldValByName("updateBy", "admin", metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", DateUtil.format(new Date(), "yyyyMMddhhmmss"), metaObject);
        this.setFieldValByName("updateBy", "admin", metaObject);
    }
}
