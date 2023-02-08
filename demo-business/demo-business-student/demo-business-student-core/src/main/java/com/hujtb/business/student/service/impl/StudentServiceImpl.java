package com.hujtb.business.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hujtb.business.student.feign.ClsFeign;
import com.hujtb.business.student.service.StudentService;
import com.hujtb.data.entity.Classes;
import com.hujtb.data.entity.Student;
import com.hujtb.data.enums.Codes;
import com.hujtb.data.mapper.StudentDao;
import com.hujtb.data.r.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 学生表服务接口实现类
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

    @Autowired
    private ClsFeign clsFeign;

    /**
     * 根据学生信息，查询出对应的班级信息
     *
     * @param id
     * @return
     */
    @Override
    public Student getById(Serializable id) {

        Student stu = super.getById(id);
        Long cId = stu.getCId();
        // 获取班级信息
        R<Classes> rCls = clsFeign.getClassByCid(cId);
        if (rCls.getCode() != Codes.SUCC.getCode()) {
            throw new RuntimeException("获取班级信息失败！");
        }
        Classes clsData = rCls.getData();
        stu.setCls(clsData);
        return stu;
    }
}
