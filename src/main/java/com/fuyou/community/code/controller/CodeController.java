package com.fuyou.community.code.controller;

import com.fuyou.community.code.model.ImageCode;
import com.fuyou.community.code.util.ImageCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/code")
@Slf4j
public class CodeController {

    @GetMapping("/img")
    public void getCode(HttpServletResponse response){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        ImageCode imageCode = imageCodeGenerator.creatCode();
        try{
            ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
        }catch (Exception e){
            log.error("验证码生成异常：{}",e.getMessage());
        }

    }
}
