package com.fuyou.community.dashboard.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fuyou.community.article.model.ArticleInfo;
import com.fuyou.community.article.model.CommentInfo;
import com.fuyou.community.article.service.ArticleService;
import com.fuyou.community.article.service.CommentService;
import com.fuyou.community.common.ResultVo;
import com.fuyou.community.dashboard.model.LineDataVo;
import com.fuyou.community.dashboard.model.TotalVo;
import com.fuyou.community.file.model.FileInfo;
import com.fuyou.community.file.service.FileInfoService;
import com.fuyou.community.user.model.User;
import com.fuyou.community.user.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/das")
@Api(tags = "首页概览")
@RequiredArgsConstructor
public class DashboardController {

    private final ArticleService articleService;
    private final UserService userService;
    private final CommentService commentService;
    private final FileInfoService fileInfoService;

    @GetMapping("/total")
    public ResultVo<TotalVo> getTotal() {
        int totalA = articleService.count();
        int totalU = userService.count();
        int totalC = commentService.count();
        int totalF = fileInfoService.count();
        TotalVo vo = new TotalVo();
        vo.setArticleTotal(totalA);
        vo.setUserTotal(totalU);
        vo.setCommentTotal(totalC);
        vo.setFileTotal(totalF);
        return ResultVo.success(2000, "获取成功", vo);
    }

    @GetMapping("/lineData")
    private ResultVo<LineDataVo> lineData() {
        List<Integer> listA = new ArrayList<>(7);
        List<Integer> listC = new ArrayList<>(7);
        List<Integer> listF = new ArrayList<>(7);
        List<Integer> listU = new ArrayList<>(7);

        Date temp = new Date();
        List<String> dateList = new ArrayList<>(7);
        int offset = 1;
        while (offset <= 7) {
            dateList.add(DateUtil.format(temp, "yyyyMMdd"));
            temp = DateUtil.offset(temp, DateField.DAY_OF_MONTH, -1);
            offset++;
        }
        dateList.stream().forEach(date -> {
            int a = articleService.count(Wrappers.lambdaQuery(ArticleInfo.class)
                    .likeRight(ArticleInfo::getCreateTime, date));
            listA.add(a);
            int u = userService.count(Wrappers.lambdaQuery(User.class)
                    .likeRight(User::getCreateTime, date));
            listU.add(u);
            int f = fileInfoService.count(Wrappers.lambdaQuery(FileInfo.class)
                    .likeRight(FileInfo::getCreateTime, date));
            listF.add(f);
            int c = commentService.count(Wrappers.lambdaQuery(CommentInfo.class)
                    .likeRight(CommentInfo::getCreateTime, date));
            listC.add(c);
        });
        ArrayList<String> dates = new ArrayList<>();
        dateList.stream().forEach(date -> {
            dates.add(DateUtil.format(DateUtil.parse(date), "yyyy年MM月dd日"));
        });
        LineDataVo lineDataVo = new LineDataVo();
        lineDataVo.setCommentData(listC);
        lineDataVo.setArticleData(listA);
        lineDataVo.setFileData(listF);
        lineDataVo.setUserData(listU);
        lineDataVo.setDateList(dates);
        return ResultVo.success(2000, "折线数据获取成功", lineDataVo);
    }

    @GetMapping("/count")
    public ResultVo<List<Integer>> count() {
        List<Integer> counts = new ArrayList<>();
        counts.add(articleService.count());
        counts.add(commentService.count());
        counts.add(fileInfoService.count());
        return ResultVo.success(2000,"获取成功",counts);
    }
}
