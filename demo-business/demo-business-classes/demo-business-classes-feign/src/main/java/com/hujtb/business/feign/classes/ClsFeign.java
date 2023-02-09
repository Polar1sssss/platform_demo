package com.hujtb.business.feign.classes;

import com.hujtb.data.entity.Classes;
import com.hujtb.data.r.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * feign客户端，name属性指定要访问的微服务名称
 */
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
