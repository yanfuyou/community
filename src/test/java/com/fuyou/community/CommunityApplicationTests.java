package com.fuyou.community;

import com.fuyou.community.user.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommunityApplicationTests {
    @Autowired
    UserController userController;

    @Test
    void contextLoads() {
//        boolean s = userController.mail("fuyou_002@163.com", "123", "测试");
//        System.out.println(s);
    }

}
