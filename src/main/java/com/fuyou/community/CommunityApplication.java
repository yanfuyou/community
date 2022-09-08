package com.fuyou.community;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.io.File;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class })
@Slf4j
public class CommunityApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CommunityApplication.class, args);
        String rootPath = System.getProperty("user.dir");
        log.info("项目目录{}",rootPath);
        String filePath = "/static/upload";
        File upload = new File(rootPath,filePath);
        if (!upload.exists()){
            log.info("创建目录：{}",upload.toString());
            if (!upload.mkdirs()){
                throw new Exception("资源目录创建失败，请检查");
            }
        }
    }

}
