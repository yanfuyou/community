package com.fuyou.community.article.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fuyou.community.CommunityApplication;
import com.fuyou.community.article.model.ArticleInfo;
import com.fuyou.community.article.service.ArticleService;
import com.fuyou.community.common.ResultVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes=CommunityApplication.class)
public class ArticleControllerTest {
    @Autowired
    private ArticleService articleService;

    @Test
    public void list() {
    }
}