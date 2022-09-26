package com.fuyou.community.user.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.user.model.User;
import com.fuyou.community.user.model.UserEduInfo;
import com.fuyou.community.user.model.UserLabelinfo;
import com.fuyou.community.user.model.UserWorkinfo;
import com.fuyou.community.user.model.dto.BaseInfoDto;
import com.fuyou.community.user.model.dto.LoginDto;
import com.fuyou.community.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true")
@Api(tags = "用户相关操作")
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ResultVo login(@RequestBody LoginDto dto) {
        if (StrUtil.isBlank(dto.getVerification())) {
            return ResultVo.fail(5000, "验证码不能为空");
        }
        String code = (String) redisTemplate.opsForValue().get(dto.getCodeUid());
        if (StrUtil.isBlank(code)) {
            return ResultVo.fail(5000, "验证码过期");
        }
        if (!code.equals(dto.getVerification())) {
            return ResultVo.fail(5000, "验证码错误");
        }
        return userService.login(dto);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id获取用户信息")
    public ResultVo<User> getUser(@PathVariable("id") String id, HttpServletResponse response) {
        User user = userService.getUserById(id);
        log.info("查询{}用户", id);
        return ResultVo.success(2000, "成功", user);
    }

    @PostMapping("/signup")
    @ApiOperation("用户注册")
    public ResultVo<Object> signUp(@RequestBody User user) {
        return userService.signUp(user);
    }

    @PostMapping("/update")
    @ApiOperation("根据id更新用户信息")
    public ResultVo<Object> updateUserById(@RequestBody User user) {
        int i = userService.updateUserById(user);
        if (i > 0) {
            return ResultVo.success(2000, "用户信息更新成功。");
        } else {
            return ResultVo.fail(5000, "用户信息更新失败");
        }
    }

    @PostMapping("/saveEdu")
    @ApiOperation("保存用户教育信息")
    public ResultVo saveEdu(@RequestBody UserEduInfo eduInfo) {
        return userService.saveEdu(eduInfo);
    }

    @PostMapping("/saveWork")
    @ApiOperation("保存用户工作信息")
    public ResultVo saveWork(@RequestBody UserWorkinfo workinfo) {
        return userService.saveWork(workinfo);
    }

    @PostMapping("/saveLabel")
    @ApiOperation("保存用户标签信息")
    public ResultVo saveLabel(@RequestBody UserLabelinfo labelinfo) {
        return userService.saveLabel(labelinfo);
    }

    @PostMapping("/updateBase")
    @ApiOperation("更新用户的基本信息")
    public ResultVo updateBaseInfo(@RequestBody BaseInfoDto baseInfoDto) {
        return userService.updateBaseinfo(baseInfoDto);
    }

    @PostMapping("/delLabel")
    @ApiOperation("移除用户标签")
    public ResultVo delLabel(String labelId) {
        return userService.delLabel(labelId);
    }

    @PostMapping("/getUserLabels")
    @ApiOperation("获取用户标签")
    public ResultVo<List<UserLabelinfo>> getUserLabels(String userId) {
        List<UserLabelinfo> userLabels = userService.getUserLabels(userId);
        return ResultVo.success(2000, "获取成功", userLabels);
    }

}
