package com.fuyou.community.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fuyou.community.sys.model.SysLabelinfo;
import com.fuyou.community.user.model.UserLabelinfo;

import java.util.List;

public interface UserLabelinfoMapper extends BaseMapper<UserLabelinfo> {
    List<SysLabelinfo> getUserLabels(String userId);
}
