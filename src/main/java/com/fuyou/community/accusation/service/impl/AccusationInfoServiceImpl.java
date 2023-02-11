package com.fuyou.community.accusation.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fuyou.community.accusation.model.AccusationInfo;
import com.fuyou.community.accusation.service.AccusationInfoService;
import com.fuyou.community.accusation.dao.AccusationInfoMapper;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.sys.model.dto.PageQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author yanfuyou
* @description 针对表【accusation_info(举报信息)】的数据库操作Service实现
* @createDate 2023-02-11 19:25:17
*/
@Service
@RequiredArgsConstructor
public class AccusationInfoServiceImpl extends ServiceImpl<AccusationInfoMapper, AccusationInfo>
    implements AccusationInfoService{

    private final AccusationInfoMapper accusationInfoMapper;

    @Override
    public ResultVo<Object> add(AccusationInfo accusationInfo) {
        accusationInfo.setId(IdUtil.simpleUUID());
        accusationInfoMapper.insert(accusationInfo);
        return ResultVo.success(2000,"举报成功");
    }

    @Override
    public ResultVo<Page<AccusationInfo>> page(PageQueryDto<AccusationInfo> dto) {
        Page<AccusationInfo> page = new Page<>();
        page.setCurrent(dto.getCurrent()).setSize(dto.getSize()).setOrders(dto.getOrders());
        Page<AccusationInfo> accusationInfoPage = accusationInfoMapper.selectPage(page, Wrappers.emptyWrapper());
        List<AccusationInfo> records = accusationInfoPage.getRecords();
        Map<String, List<AccusationInfo>> collect = records.stream().collect(Collectors.groupingBy(AccusationInfo::getRelId));
        records.clear();
        for(String key : collect.keySet()){
            records.addAll(collect.get(key));
        }
        accusationInfoPage.setRecords(records);
        return ResultVo.success(2000,"查询成功",accusationInfoPage);
    }
}




