package com.fuyou.community.user.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.exception.ServiceException;
import com.fuyou.community.menu.model.MenuInfo;
import com.fuyou.community.role.model.RoleInfo;
import com.fuyou.community.role.service.RoleInfoService;
import com.fuyou.community.sys.model.PageDto;
import com.fuyou.community.sys.model.SysLabelinfo;
import com.fuyou.community.sys.model.dto.DelLabelDto;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import com.fuyou.community.sys.util.CurrentUtil;
import com.fuyou.community.sys.util.PasswordUtil;
import com.fuyou.community.user.dao.UserEduInfoMapper;
import com.fuyou.community.user.dao.UserLabelinfoMapper;
import com.fuyou.community.user.dao.UserMapper;
import com.fuyou.community.user.dao.UserWorkinfoMapper;
import com.fuyou.community.user.model.User;
import com.fuyou.community.user.model.UserEduInfo;
import com.fuyou.community.user.model.UserLabelinfo;
import com.fuyou.community.user.model.UserWorkinfo;
import com.fuyou.community.user.model.dto.BaseInfoDto;
import com.fuyou.community.user.model.dto.LoginDto;
import com.fuyou.community.user.model.vo.AvatarVo;
import com.fuyou.community.user.model.vo.InfoVo;
import com.fuyou.community.user.model.vo.LoginVO;
import com.fuyou.community.user.model.vo.UserScoreVo;
import com.fuyou.community.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.INTERNAL;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {


    private final UserMapper userMapper;
    private final UserEduInfoMapper userEduInfoMapper;
    private final UserWorkinfoMapper userWorkinfoMapper;
    private final UserLabelinfoMapper userLabelinfoMapper;

    private final RoleInfoService roleInfoService;


    @Override
    public User getUserById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public ResultVo login(LoginDto dto) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, dto.getUserName()));
        if (ObjectUtil.isEmpty(user)) {
            return ResultVo.fail(4000, "用户不存在,请先注册");
        } else {
            String excr = PasswordUtil.excr(dto.getUserPassword(), user.getUserSalt());
            if (!user.getUserPassword().equals(excr)) {
                return ResultVo.fail(5000, "密码错误");
            }
        }
//        注入redis登录凭证
        StpUtil.login(user.getId(),JSON.toJSONString(user));
        LoginVO loginVO = new LoginVO();
        loginVO.setUserId(user.getId());
        loginVO.setUserName(user.getUserName());
        loginVO.setUserAlias(user.getUserAlias());
        loginVO.setTokenId(StpUtil.getTokenValue());
        loginVO.setUserAvatar(user.getUserAvatar());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return ResultVo.success(2000, "登录成功", loginVO);
    }

    @Override
    public ResultVo logout() {
        StpUtil.logout();
        return ResultVo.success(2000,"退出成功");
    }

    @Override
    public ResultVo signUp(User user) {
        User old = userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getUserName, user.getUserName()));
        if (ObjectUtil.isNotEmpty(old)) {
            return ResultVo.fail(5000, "用户名已存在");
        }
        user.setId(IdUtil.simpleUUID());
//        盐值
        String salt = RandomUtil.randomString(5);
        String excr = PasswordUtil.excr(user.getUserPassword(), salt);
        user.setUserPassword(excr);
        user.setUserSalt(salt);
        user.setCreateBy(user.getUserName());
        user.setUpdateBy(user.getUserName());
        int insert = userMapper.insert(user);
        if (insert < 1) {
            return ResultVo.fail(5000, "用户注册失败");
        }
        return ResultVo.success(2000, "注册成功");
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public ResultVo saveEdu(UserEduInfo eduInfo) {
        UserEduInfo oldEduInfo = userEduInfoMapper.selectOne(Wrappers.lambdaQuery(UserEduInfo.class)
                .eq(UserEduInfo::getUserId, eduInfo.getUserId()));
        if (ObjectUtil.isEmpty(oldEduInfo)) {
            eduInfo.setId(IdUtil.simpleUUID());
            userEduInfoMapper.insert(eduInfo);
        }
        {
            userEduInfoMapper.update(eduInfo, Wrappers.lambdaUpdate(UserEduInfo.class)
                    .eq(UserEduInfo::getUserId, eduInfo.getUserId()));
        }
        return ResultVo.success(2000, "更新成功");
    }

    @Override
    public ResultVo saveWork(UserWorkinfo workinfo) {
        UserWorkinfo userWorkinfo = userWorkinfoMapper.selectOne(Wrappers.lambdaQuery(UserWorkinfo.class)
                .eq(UserWorkinfo::getUserId, workinfo.getUserId()));
        if (ObjectUtil.isEmpty(userWorkinfo)) {
            userWorkinfoMapper.insert(workinfo);
        } else {
            userWorkinfoMapper.update(workinfo, Wrappers.lambdaUpdate(UserWorkinfo.class)
                    .eq(UserWorkinfo::getUserId, workinfo.getUserId()));
        }
        return ResultVo.success(2000, "更新成功");
    }

    @Override
    public ResultVo saveLabel(UserLabelinfo labelinfo) {
        int insert = userLabelinfoMapper.insert(labelinfo);
        return insert > 0 ? ResultVo.success(2000, "保存成功") : ResultVo.fail(5000, "保存失败");
    }

    @Override
    public ResultVo updateBaseinfo(BaseInfoDto dto) {
        int update = userMapper.update(null, Wrappers.lambdaUpdate(User.class)
                .eq(User::getId, dto.getId())
                .set(User::getUserName, dto.getUserName())
                .set(User::getUserAlias, dto.getUserAlias())
                .set(User::getUserSex, dto.getUserSex())
                .set(User::getUserSign, dto.getUserSign())
                .set(User::getUserEmail,dto.getUserEmail()));
        return update > 0 ? ResultVo.success(2000, "更新成功") : ResultVo.fail(5000, "更新失败");
    }

    public ResultVo delLabel(DelLabelDto dto){
        userLabelinfoMapper.delete(Wrappers.lambdaQuery(UserLabelinfo.class)
                .eq(UserLabelinfo::getUserId,dto.getUserId())
                .eq(UserLabelinfo::getLabelId,dto.getLabelId()));
        return ResultVo.success(2000,"移除成功");
    }

    @Override
    public List<SysLabelinfo> getUserLabels(String userID) {
        List<SysLabelinfo> userLabelinfos = userLabelinfoMapper.getUserLabels(userID);
        return userLabelinfos;
    }

    @Override
    public UserEduInfo getEduInfo(String id) {
        return userEduInfoMapper.selectOne(Wrappers.lambdaQuery(UserEduInfo.class)
                .eq(UserEduInfo::getUserId,id));
    }

    @Override
    public UserWorkinfo getWorkInfo(String id) {
        return userWorkinfoMapper.selectOne(Wrappers.lambdaQuery(UserWorkinfo.class)
                .eq(UserWorkinfo::getUserId,id));
    }

    @Override
    public IPage<AvatarVo> getUserAvatars(PageDto<Set<String>> avatarDto) {
        Page<AvatarVo> page = new Page<>();
        page.setCurrent(avatarDto.getCurrent());
        page.setSize(avatarDto.getSize());
        page.setHitCount(true);
        return userMapper.getUserAvatars(page,avatarDto);
    }

    @Override
    public ResultVo<InfoVo> getInfo(String userId) {
        User user;
        if (StrUtil.isBlank(userId)){
            user = CurrentUtil.getLoginUser();
            userId = CurrentUtil.getLoginUser().getId();
        }else{
            user = userMapper.selectOne(Wrappers.lambdaQuery(User.class).eq(User::getId,userId));
        }
        List<RoleInfo> userRoleList = roleInfoService.getRoleList(userId);
        if (ObjectUtil.isEmpty(user)){
            throw new ServiceException(5000,"获取用户信息失败");
        }
        InfoVo infoVo = new InfoVo();
        infoVo.setAvatar(user.getUserAvatar());
        infoVo.setName(user.getUserAlias());
        infoVo.setIntroduction(user.getUserSign());
        if (CollUtil.isNotEmpty(userRoleList)){
            List<String> roleList = userRoleList.stream().map(RoleInfo::getRoleName).collect(Collectors.toList());
            infoVo.setRoles(roleList);
            //        获取角色路由
            Map<String,List<String>> map = new HashMap<>();
            userRoleList.stream().forEach(role->{
                List<MenuInfo> menuInfos = roleInfoService.roleMenu(role.getRoleId());
                if (CollUtil.isNotEmpty(menuInfos)){
                    List<String> collect = menuInfos.stream().map(MenuInfo::getPermission).collect(Collectors.toList());
                    map.put(role.getRoleName(),collect);
                }
            });
            infoVo.setPermission(map);
        }


        return ResultVo.success(2000,"获取用户信息成功",infoVo);
    }

    @Override
    public ResultVo<UserScoreVo> score(String userId) {
        int all = userMapper.score(userId, null);
        String month = DateUtil.format(new Date(), "yyyyMM");
        Integer thisMonth = userMapper.score(userId, month);
        UserScoreVo userScoreVo = new UserScoreVo();
        userScoreVo.setAll(all);
        userScoreVo.setThisMonth(Optional.of(thisMonth).orElse(0));
        return ResultVo.success(2000,"查询成功",userScoreVo);
    }

    @Override
    public ResultVo<Page<User>> userMini(PageQueryDto<User> dto) {
        Page<User> page = new Page<>();
        page.setCurrent(dto.getCurrent()).setSize(dto.getSize()).setOrders(dto.getOrders());
        return ResultVo.success(2000,"获取成功",userMapper.userMini(page,dto));
    }
}
