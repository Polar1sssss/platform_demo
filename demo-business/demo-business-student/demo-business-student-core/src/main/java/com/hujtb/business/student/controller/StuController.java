package com.hujtb.business.student.controller;

import com.hujtb.commons.web.r.R;
import com.hujtb.commons.web.r.RUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stu")
@Slf4j
public class StuController {

    @RequestMapping("/list")
    public R getStudent() {

        log.info("测试打印日志信息");
        return RUtils.create("学生数据");
    }

    @RequestMapping("/login")
    public R login(String username, String password) {

        log.info("[login info] 登录用户名：{}， 密码：{}", username, password);
        return RUtils.create("登录成功~");
    }
}
