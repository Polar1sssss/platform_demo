package com.hujtb.business.feign.score;

import com.hujtb.data.r.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("demo-score")
public interface ScoreFeign {

    @RequestMapping("/score/getBySid")
    R<Double> getScoreBySid(@RequestParam(value = "sId") Long sId);
}
