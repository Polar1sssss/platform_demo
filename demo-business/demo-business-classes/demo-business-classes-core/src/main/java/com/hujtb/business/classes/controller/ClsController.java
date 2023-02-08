package com.hujtb.business.classes.controller;

import com.hujtb.business.classes.service.ClassesService;
import com.hujtb.data.entity.Classes;
import com.hujtb.data.r.R;
import com.hujtb.data.r.RUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/cls")
public class ClsController {

    @Autowired
    private ClassesService classesService;

    @RequestMapping("/getById")
    public R<Classes> getClassByCid(@NotEmpty(message = "班级id不能为空") Long cId) {
        Classes classes = classesService.getById(cId);
        return RUtils.create(classes);
    }
}
