package com.qiao.gaokaoforecast.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-08-02 18:38
 * @since 0.1.0
 *
 * 用于存放每年的高考录取批次的分数线-实体类
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreBatch {
    private int batchWenFirst;
    private int batchWenSecond;
    private int batchLiFirst;
    private int batchLiSecond;
    private int year;
}
