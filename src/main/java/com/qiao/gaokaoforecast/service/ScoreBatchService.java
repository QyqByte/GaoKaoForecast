package com.qiao.gaokaoforecast.service;

import com.qiao.gaokaoforecast.domain.ScoreBatch;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-08-02 19:10
 * @since 0.1.0
 **/
public interface ScoreBatchService {
    List<ScoreBatch> getEveryYearBatch();

    ScoreBatch getbatchByYear(int year);

    int addBatchYear(ScoreBatch scoreBatch);
}
