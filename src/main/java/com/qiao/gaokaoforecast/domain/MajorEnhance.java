package com.qiao.gaokaoforecast.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-08-18 12:52
 * @since 0.1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MajorEnhance {
    private Major nowmajor;
    private int beforeMajorMinScore;
    private int moreBeforeMajorMinScore;
    private int beforeMajorMinSection;
    private int moreBeforeMajorMinSection;
    private String beforeMajorAverage;
    private String moreBeforeMajoraverage;

}
