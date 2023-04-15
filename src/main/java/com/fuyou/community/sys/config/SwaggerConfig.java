package com.fuyou.community.sys.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yanfuyou
 * @Description swagger配置类
 * @Date 下午9:11 2022/9/8
 */
@Configuration
@EnableSwagger2WebMvc
@ConditionalOnProperty(name = "swagger.enable",havingValue = "true")
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        List<Parameter> parameters = new ArrayList<>();
        ParameterBuilder auth = new ParameterBuilder();
        auth.name("Authorization")
                .description("token")
                .required(true)
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .build();
        parameters.add(auth.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径,控制器类包
//                .apis(RequestHandlerSelectors.basePackage("com.fuyou.community.user.controller"))
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }

    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("毕设接口文档")
                //创建人
                .contact(new Contact("鄢富友", "", "fuyou_002@163.com"))
                //版本号
                .version("1.0")
                //描述
                .description("毕设api文档")
                .build();
    }

}


