package com.fuyou.community.sys.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yanfuyou
 * @Description mybatisplus配置类
 * @Date 下午9:10 2022/9/8
 */
@Configuration
@MapperScan({"com.fuyou.community.*.dao"})
public class MybatisPlusConfig {
}
