package com.fuyou.community.sys.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")  /*与application.yaml绑定*/
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean
    /*后台监控功能 web.xml*/
//    因为springboot内置了servlet容器，所以没有web.xml
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*"); //获取后台监控
        //后台需要有人登陆查看，配置账号、密码
        Map<String, String> map = new HashMap<>();
        map.put("loginUsername","yanfuyou");  //登陆的key是固定的
        map.put("loginPassword","test");
        /*允许谁可以访问，若参数为空，则表明任何人都可以访问*/
        map.put("allow","");
        /*禁止谁访问*/
        map.put("mm","192.168.12.3");  //禁止ip地址访问配置
        bean.setInitParameters(map);  //设置初始化参数
        return bean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        /*可以过滤掉哪些请求*/
        Map<String, String> map = new HashMap<>();
        //这些东西不进行统计
        map.put("exclusions","*.js,*.css,/druid/*");
        return filterRegistrationBean;
    }

}


