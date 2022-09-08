package com.fuyou.community.user.controller;

import com.fuyou.community.common.ResultVo;
import com.fuyou.community.user.model.User;
import com.fuyou.community.user.model.dto.LoginDto;
import com.fuyou.community.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("用户控制器")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public User login(@RequestBody LoginDto dto){
        return new User();
    }

    @GetMapping("/user/{id}")
    public ResultVo<User> getUser(@PathVariable("id") String id){
        User user = userService.getUserById(id);
        log.info("查询{}用户",id);
        return ResultVo.success(2000,"成功",user);
    }
}
