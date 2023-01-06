package com.hujtb.business.student.controller;

import com.hujtb.business.student.input.StudentInput;
import com.hujtb.business.student.service.StudentService;
import com.hujtb.commons.web.enums.Codes;
import com.hujtb.commons.web.r.R;
import com.hujtb.commons.web.r.RUtils;
import com.hujtb.data.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/stu")
@Slf4j
@Validated
public class StuController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/list")
    public R getStudent() {

        List<Student> list = studentService.list();
        return RUtils.create(list);
    }

    @RequestMapping("/login")
    public R login(@NotBlank(message = "用户名不能为空") String username, @NotBlank(message = "密码不能为空") String password) {

        log.info("[login info] 登录用户名：{}， 密码：{}", username, password);
        return RUtils.create("登录成功~");
    }

    @RequestMapping("/insert")
    public R insert(@Valid @RequestBody StudentInput stu) {

        log.info("[stu-insert] 学生添加：{}", stu);
        Student student = new Student();
        BeanUtils.copyProperties(stu, student);
        studentService.save(student);
        return RUtils.create("添加成功~");
    }
}
