package com.fuyou.community.config;

import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JasyptConfigTest {
    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void pwdTest(){
        String yanfuyou = encryptor.encrypt("yanfuyou");
        String password = encryptor.encrypt("d40131522f6ee578");

        System.out.println("pa-" +  password + "us-" +yanfuyou);
    }
}