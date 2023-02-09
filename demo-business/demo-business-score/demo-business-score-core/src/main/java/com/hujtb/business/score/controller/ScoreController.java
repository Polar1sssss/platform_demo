package com.hujtb.business.score.controller;

import com.hujtb.data.r.R;
import com.hujtb.data.r.RUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @RequestMapping("/getBySid")
    public R<Double> getScoreBySid(Long sId) {

        double score = 0.0;

        switch (sId.intValue()) {
            case 1:
                score = 8.9;
                break;
            case 2:
                score = 9.0;
                break;
        }
        return RUtils.create(score);
    }
}
