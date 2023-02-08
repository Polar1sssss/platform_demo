package com.hujtb.business.student.feign;

import com.hujtb.data.entity.Classes;
import com.hujtb.data.r.R;
import org.springframework.cglib.core.ClassesKey;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "demo-classes")
public interface ClsFeign {

    /**
     * 根据班级id远程查询班级信息
     *
     * @param cId
     * @return
     */
    @RequestMapping("/cls/getById")
    R<Classes> getClassByCid(@RequestParam("cId") Long cId);
}
