package com.fuyou.community.sys.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 参数校验配置类
 */
@Configuration
public class ValidatorConfig {
    /**
     * @Author yanfuyou
     * @Description 一个参数失败即返回
     * @Date 下午11:25 2022/11/8
     */
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation
                .byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;
    }
}
