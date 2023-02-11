package com.fuyou.community;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
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

    @Data
    @Accessors(chain = true)
    class User{
        private Integer id;
        private String name;
        private Integer age;
    }
    @Test
    public void t(){
        List<User> listUser = new ArrayList<>();
        listUser.add(new User().setId(1).setName("张三").setAge(18));
        listUser.add(new User().setId(2).setName("李四").setAge(25));
        listUser.add(new User().setId(3).setName("王五").setAge(25));
        listUser.add(new User().setId(3).setName("马六").setAge(31));
        Map<Integer, List<User>> collect = listUser.stream().collect(Collectors.groupingBy(User::getId));
        log.info("{}",collect);
    }
}
