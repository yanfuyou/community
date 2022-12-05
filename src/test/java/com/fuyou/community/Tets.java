package com.fuyou.community;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tets {
    @Test
    public void lineData() {
        Date temp = new Date();
        List<String> dateList = new ArrayList<>(7);
        int offset = 1;
        while (offset <= 7) {
            dateList.add(DateUtil.format(temp,"yyyyMMdd"));
            temp = DateUtil.offset(temp, DateField.DAY_OF_MONTH, -1);
            offset++;
        }
        dateList.stream().forEach(System.out::println);
    }
}
