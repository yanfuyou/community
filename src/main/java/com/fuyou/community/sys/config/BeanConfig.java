package com.fuyou.community.sys.config;

import lombok.Data;
import org.omg.CORBA.INTERNAL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ConfigurationProperties(prefix = "spring.mail")
@Data
public class BeanConfig {
    private String host;
    private int port;
    private String username;
    private String password;
    private String protocol;
    private String defaultEncoding;

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setProtocol(protocol);
        javaMailSender.setDefaultEncoding(defaultEncoding);
        return javaMailSender;
    }
}
