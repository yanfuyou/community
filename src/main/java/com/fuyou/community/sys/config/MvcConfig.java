package com.fuyou.community.sys.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.fuyou.community.exception.ServiceException;
import com.fuyou.community.sys.auth.AuthUtil;
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
     * 拦截器配置
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {//    使用sa-token拦截器
        try {
            registry.addInterceptor(new SaInterceptor(handle -> {
                        SaRouter.match("/**")
                                .notMatch("/user/**", "/upload/**", "/favicon.ico", "/v2/**", "/swagger-resources/**", "/webjars/**", "/doc.html", "/error/**", "/code/**", "/user/login", "/user/signup", "/image/**", "/file/**", "article/**")
                                .check(rule -> AuthUtil.authMethod());
                    }))
                    .addPathPatterns("/**");
        }catch (NotLoginException e){
            throw new ServiceException(401,"请登录");
        }
    }
}
