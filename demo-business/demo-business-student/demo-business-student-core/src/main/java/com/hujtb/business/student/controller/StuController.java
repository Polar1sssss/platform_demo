package com.hujtb.business.student.controller;

import com.hujtb.business.student.input.StudentInput;
import com.hujtb.commons.web.enums.Codes;
import com.hujtb.commons.web.r.R;
import com.hujtb.commons.web.r.RUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/stu")
@Slf4j
@Validated
public class StuController {

    @RequestMapping("/list")
    public R getStudent() {

        log.info("测试打印日志信息");
        return RUtils.create("学生数据");
    }

    @RequestMapping("/login")
    public R login(@NotBlank(message = "用户名不能为空") String username,
                   @NotBlank(message = "密码不能为空") String password) {

        log.info("[login info] 登录用户名：{}， 密码：{}", username, password);
        return RUtils.create("登录成功~");
    }

    @RequestMapping("/insert")
    public R insert(@Valid @RequestBody StudentInput stu) {

        log.info("[stu-insert] 学生添加：{}", stu);
        System.out.println("调用业务");
        return RUtils.create("添加成功~");
    }
}
