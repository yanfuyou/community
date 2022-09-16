package com.fuyou.community.user.controller;

import com.fuyou.community.common.ResultVo;
import com.fuyou.community.user.model.User;
import com.fuyou.community.user.model.dto.LoginDto;
import com.fuyou.community.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(allowCredentials = "true")
@Api(tags = "用户相关操作")
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ResultVo login(@RequestBody(required = true) LoginDto dto){
        System.out.println(dto);
        return userService.login(dto);
    }

    @GetMapping("/user/{id}")
    public ResultVo<User> getUser(@PathVariable("id") String id, HttpServletResponse response){
        User user = userService.getUserById(id);
        log.info("查询{}用户",id);
        return ResultVo.success(2000,"成功",user);
    }

    @PostMapping("/signup")
    @ApiOperation("用户注册")
    public ResultVo signUp(@RequestBody User user){
        return userService.signUp(user);
    }
}
