package com.fuyou.community.user.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.sys.model.PageDto;
import com.fuyou.community.sys.model.SysLabelinfo;
import com.fuyou.community.sys.model.dto.DelLabelDto;
import com.fuyou.community.user.model.User;
import com.fuyou.community.user.model.UserEduInfo;
import com.fuyou.community.user.model.UserLabelinfo;
import com.fuyou.community.user.model.UserWorkinfo;
import com.fuyou.community.user.model.dto.BaseInfoDto;
import com.fuyou.community.user.model.dto.LoginDto;
import com.fuyou.community.user.model.vo.AvatarVo;
import com.fuyou.community.user.model.vo.UserBasicVo;
import com.fuyou.community.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

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
    public ResultVo<UserBasicVo> getUser(@PathVariable("id") String id, HttpServletResponse response) {
        User user = userService.getUserById(id);
        UserBasicVo vo = new UserBasicVo();
        if(ObjectUtil.isNotEmpty(user)){
            BeanUtil.copyProperties(user,vo);
        }
        return ResultVo.success(2000, "获取用户基本信息成功", vo);
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
    public ResultVo saveWork(@RequestBody UserWorkinfo workInfo) {
        return userService.saveWork(workInfo);
    }

    @GetMapping("/saveLabel/{userId}/{labelId}")
    @ApiOperation("保存用户标签信息")
    public ResultVo saveLabel(@PathVariable("userId")String userId,@PathVariable("labelId")String labelId) {
        UserLabelinfo userLabelinfo = new UserLabelinfo();
        userLabelinfo.setUserId(userId);
        userLabelinfo.setLabelId(labelId);
        return userService.saveLabel(userLabelinfo);
    }

    @PostMapping("/updateBase")
    @ApiOperation("更新用户的基本信息")
    public ResultVo updateBaseInfo(@RequestBody BaseInfoDto baseInfoDto) {
        return userService.updateBaseinfo(baseInfoDto);
    }

    @PostMapping("/delLabel")
    @ApiOperation("移除用户标签")
    public ResultVo delLabel(@RequestBody DelLabelDto dto) {
        return userService.delLabel(dto);
    }

    @GetMapping("/getUserLabels/{userId}")
    @ApiOperation("获取用户标签")
    public ResultVo<List<SysLabelinfo>> getUserLabels(@PathVariable("userId") String userId) {
        List<SysLabelinfo> userLabels = userService.getUserLabels(userId);
        return ResultVo.success(2000, "获取成功", userLabels);
    }

    @GetMapping("/edu/{id}")
    @ApiOperation("获取用户教育信息")
    public ResultVo<UserEduInfo> getEduInfo(@PathVariable("id")String id){
        UserEduInfo eduInfo = userService.getEduInfo(id);
        return ResultVo.success(2000,"获取用户教育信息成功",eduInfo);
    }

    @GetMapping("/work/{id}")
    @ApiOperation("获取用户工作信息")
    public ResultVo<UserWorkinfo> getWork(@PathVariable("id") String id){
        UserWorkinfo workInfo = userService.getWorkInfo(id);
        return ResultVo.success(2000,"获取用户工作信息成功",workInfo);
    }

    @PostMapping("/getAvatars")
    @ApiOperation("获取用户头像")
    public IPage<AvatarVo> getUserAvatars(@RequestBody PageDto<Set<String>> avatarDto){
        return userService.getUserAvatars(avatarDto);
    }

}
