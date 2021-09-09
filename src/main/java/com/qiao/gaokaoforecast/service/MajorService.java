package com.qiao.gaokaoforecast.service;

import com.qiao.gaokaoforecast.domain.Major;
import com.qiao.gaokaoforecast.domain.MajorEnhance;

import java.util.List;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-07-29 14:02
 * @since 0.1.0
 **/
public interface MajorService {
    int addMajor(Major major);

    List<Major> selectMajorBySchool(int schoolid);

    List<MajorEnhance> selectMajorByRank(int rank, int scope);

    List<MajorEnhance> selectMajorByScore(int diffscore,int scope,byte batch);
}
