package com.fuyou.community.config;

import com.fuyou.community.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @Author yanfuyou
 * @Description mvc配置类
 * @Date 下午9:11 2022/9/8
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String rootPath = System.getProperty("user.dir");
        File file = new File(rootPath, "static");
        String path = "file:" + file + File.separator;
        registry.addResourceHandler("/**").addResourceLocations("classpath:META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/", path);
    }

    /**
     * @Author yanfuyou
     * @Description 拦截器，在此处初始化bean是为了解决redisTemplate注入报空的问题
     * @Date 下午10:26 2022/9/12
     * @Return 登录拦截器
     */
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    /**
     * 拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/favicon.ico","/v2/**","/swagger-resources/**","/webjars/**","/doc.html","/error/**","/code/**","/user/login","/user/signup","/image/**","/file/**","article/detail/**");
    }


}
