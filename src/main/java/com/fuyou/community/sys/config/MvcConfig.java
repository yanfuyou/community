package com.fuyou.community.sys.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.fuyou.community.exception.ServiceException;
import com.fuyou.community.sys.auth.AuthUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.List;

/**
 * @Author yanfuyou
 * @Description mvc配置类
 * @Date 下午9:11 2022/9/8
 */
@Configuration
@ConfigurationProperties(prefix = "not-match-path")
@PropertySource(value = "classpath:application.yml")
@Data
public class MvcConfig implements WebMvcConfigurer {

    private final List<String> pathList;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String rootPath = System.getProperty("user.dir");
        File file = new File(rootPath, "static");
        String path = "file:" + file + File.separator;
        registry.addResourceHandler("/**").addResourceLocations("classpath:META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/", path);
    }

    /**
     * 拦截器配置
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {//    使用sa-token拦截器
        System.out.println("读取开放api：");
        pathList.stream().forEach(System.out::println);
        try {
            registry.addInterceptor(new SaInterceptor(handle -> {
                        SaRouter.match("/**")
                                .notMatch(pathList)
                                .check(rule -> AuthUtil.authMethod());
                    }))
                    .addPathPatterns("/**");
        }catch (NotLoginException e){
            throw new ServiceException(401,"请登录");
        }
    }
}
