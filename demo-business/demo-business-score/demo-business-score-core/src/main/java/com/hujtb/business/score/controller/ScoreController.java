package com.hujtb.business.score.controller;

import com.hujtb.commons.web.apiversion.ApiVersion;
import com.hujtb.data.r.R;
import com.hujtb.data.r.RUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @ApiVersion(1.0)
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

    @ApiVersion(2.0)
    @RequestMapping("/getBySid")
    public R<Double> getScoreBySid2(Long sId) {

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
