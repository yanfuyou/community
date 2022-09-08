package com.fuyou.community.user.controller;

import com.fuyou.community.user.model.User;
import com.fuyou.community.user.model.dto.LoginDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("用户控制器")
public class UserController {
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public User login(@RequestBody LoginDto dto){
        return new User();
    }
}
