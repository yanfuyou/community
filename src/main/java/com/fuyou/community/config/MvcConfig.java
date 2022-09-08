package com.fuyou.community.config;

import org.springframework.context.annotation.Configuration;
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
}
