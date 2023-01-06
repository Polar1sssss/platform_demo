package com.hujtb.business.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hujtb.business.student.service.StudentService;
import com.hujtb.data.entity.Student;
import com.hujtb.data.mapper.StudentDao;
import org.springframework.stereotype.Service;

/**
 * 学生表服务接口实现类
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {
}
