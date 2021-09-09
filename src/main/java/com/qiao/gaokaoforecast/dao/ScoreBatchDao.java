package com.qiao.gaokaoforecast.dao;

import com.qiao.gaokaoforecast.domain.ScoreBatch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-08-02 18:52
 * @since 0.1.0
 **/
@Mapper
public interface ScoreBatchDao {
    List<ScoreBatch> selectAllYears();

    ScoreBatch selectBatchByYear(int year);

    int addNewYearBatch(ScoreBatch scoreBatch);
}
