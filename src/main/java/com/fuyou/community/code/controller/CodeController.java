package com.fuyou.community.code.controller;

import com.fuyou.community.code.model.ImageCode;
import com.fuyou.community.code.util.ImageCodeGenerator;
import com.fuyou.community.common.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/code")
@Slf4j
@Api(tags = "验证码")
@RequiredArgsConstructor
public class CodeController {

    private final RedisTemplate<String,Object> redisTemplate;

    @GetMapping("/img")
    @ApiOperation("后端直接绘制验证码")
    public void getCode(HttpServletResponse response){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        ImageCode imageCode = imageCodeGenerator.creatCode();
        try{
            ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
        }catch (Exception e){
            log.error("验证码生成异常：{}",e.getMessage());
        }
    }
    @GetMapping("/num")
    @ApiOperation("由前端生成图片")
    public ResultVo<Map<String,String>> getNum(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        ImageCode imageCode = imageCodeGenerator.creatCode();
        Map<String,String> code = new HashMap<>();
        code.put("uid", "code:"+UUID.randomUUID().toString());
        code.put("codeNum",imageCode.getCode());
        redisTemplate.opsForValue().set(code.get("uid"),code.get("codeNum"),1, TimeUnit.MINUTES);
        return ResultVo.success(2000,"验证码获取成功",code);
    }

}
