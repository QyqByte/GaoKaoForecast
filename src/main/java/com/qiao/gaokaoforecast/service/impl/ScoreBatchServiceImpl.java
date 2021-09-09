package com.qiao.gaokaoforecast.service.impl;

import com.qiao.gaokaoforecast.dao.ScoreBatchDao;
import com.qiao.gaokaoforecast.domain.ScoreBatch;
import com.qiao.gaokaoforecast.service.ScoreBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-08-02 19:05
 * @since 0.1.0
 **/
@Service
public class ScoreBatchServiceImpl implements ScoreBatchService {
    @Autowired
    private ScoreBatchDao scoreBatchDao;

    @Override
    public List<ScoreBatch> getEveryYearBatch(){
        return scoreBatchDao.selectAllYears();
    }
    @Override
    public ScoreBatch getbatchByYear(int year){
        return scoreBatchDao.selectBatchByYear(year);
    }
    @Override
    public int addBatchYear(ScoreBatch scoreBatch){
        return scoreBatchDao.addNewYearBatch(scoreBatch);
    }
}
