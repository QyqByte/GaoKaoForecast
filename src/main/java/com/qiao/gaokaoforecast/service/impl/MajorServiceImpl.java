package com.qiao.gaokaoforecast.service.impl;

import com.qiao.gaokaoforecast.dao.MajorDao;
import com.qiao.gaokaoforecast.domain.Major;
import com.qiao.gaokaoforecast.domain.MajorEnhance;
import com.qiao.gaokaoforecast.domain.ScoreBatch;
import com.qiao.gaokaoforecast.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-07-29 14:21
 * @since 0.1.0
 **/
@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorDao majorDao;

    @Autowired
    private ScoreBatchServiceImpl scoreBatchService;

    private Calendar calendar = Calendar.getInstance();
    private int current_year = calendar.get(Calendar.YEAR);

    @Override
    public int addMajor(Major major) {
        return majorDao.insertMajor(major);
    }

    @Override
    public List<Major> selectMajorBySchool(int schoolid) {

        return majorDao.selectAllMajorBySchoolId(schoolid);
    }

    @Override
    public List<MajorEnhance> selectMajorByRank(int rank,int scope) {

        // 分别得到最近三年的推荐结果
        List<Major> currentlist = majorDao.selectAllMajorByScoreRank(rank, scope, current_year-1);
        List<Major> beforelist = majorDao.selectAllMajorByScoreRank(rank, scope, current_year - 2);
        List<Major> morebeforelist = majorDao.selectAllMajorByScoreRank(rank, scope, current_year - 3);

        currentlist.retainAll(beforelist);
        currentlist.retainAll(morebeforelist);


        // 这里如果出现了重复的key，则让后一个key更新上一个
        Map<Major,Major> map1 = beforelist.stream().collect(Collectors.toMap(major -> major, major -> major,(last,next)->next));
        Map<Major,Major> map2 = morebeforelist.stream().collect(Collectors.toMap(major -> major, major -> major,(last,next)->next));
        List<MajorEnhance> res = new ArrayList<>();

        for (Major major : currentlist) {
            MajorEnhance majorEnhance = new MajorEnhance();
            majorEnhance.setNowmajor(major);

            Major beforeMajor = map1.get(major);
            if(beforeMajor!=null){
                majorEnhance.setBeforeMajorAverage(beforeMajor.getMajor_average());
                majorEnhance.setBeforeMajorMinScore(beforeMajor.getMajor_min_score());
                majorEnhance.setBeforeMajorMinSection(beforeMajor.getMajor_min_section());
            }
            Major moreBeforeMajor = map2.get(major);
            if(moreBeforeMajor!=null){
                majorEnhance.setMoreBeforeMajoraverage(moreBeforeMajor.getMajor_average());
                majorEnhance.setMoreBeforeMajorMinScore(moreBeforeMajor.getMajor_min_score());
                majorEnhance.setMoreBeforeMajorMinSection(moreBeforeMajor.getMajor_min_section());
            }

            res.add(majorEnhance);
        }
        return res;
    }

    @Override
    public List<MajorEnhance> selectMajorByScore(int diffscore,int scope,byte batch) {
        // 分别得到最近三年的推荐结果

        // 先计算每年所对应的具体的该分数差的实际值
        ScoreBatch cur0 = scoreBatchService.getbatchByYear(current_year - 1);
        ScoreBatch cur1 = scoreBatchService.getbatchByYear(current_year - 2);
        ScoreBatch cur2 = scoreBatchService.getbatchByYear(current_year - 3);
        int score1 = 0;
        int score2 = 0;
        int score3 = 0;
        // batch为0表示是在一本的分数上，为1表示是在二本的分数上
        if(batch==0){
            score1 = cur0.getBatchLiFirst()+diffscore;
            score2 = cur1.getBatchLiFirst()+diffscore;
            score3 = cur2.getBatchLiFirst()+diffscore;
        }else{
            score1 = cur0.getBatchLiSecond()+diffscore;
            score2 = cur1.getBatchLiSecond()+diffscore;
            score3 = cur2.getBatchLiSecond()+diffscore;
        }
        // 然后根据每年的不同的分数进行查找
        List<Major> currentlist = majorDao.selectMajorByScore(score1, scope, current_year-1);
        List<Major> beforelist = majorDao.selectMajorByScore(score2, scope, current_year - 2);
        List<Major> morebeforelist = majorDao.selectMajorByScore(score3, scope, current_year - 3);


        // 最后进行筛选即可
        currentlist.retainAll(beforelist);
        currentlist.retainAll(morebeforelist);

        // 这里如果出现了重复的key，则让后一个key更新上一个
        Map<Major,Major> map1 = beforelist.stream().collect(Collectors.toMap(major -> major, major -> major,(last,next)->next));
        Map<Major,Major> map2 = morebeforelist.stream().collect(Collectors.toMap(major -> major, major -> major,(last,next)->next));
        List<MajorEnhance> res = new ArrayList<>();

        for (Major major : currentlist) {
            MajorEnhance majorEnhance = new MajorEnhance();
            majorEnhance.setNowmajor(major);

            Major beforeMajor = map1.get(major);
            if(beforeMajor!=null){
                majorEnhance.setBeforeMajorAverage(beforeMajor.getMajor_average());
                majorEnhance.setBeforeMajorMinScore(beforeMajor.getMajor_min_score());
                majorEnhance.setBeforeMajorMinSection(beforeMajor.getMajor_min_section());
            }
            Major moreBeforeMajor = map2.get(major);
            if(moreBeforeMajor!=null){
                majorEnhance.setMoreBeforeMajoraverage(moreBeforeMajor.getMajor_average());
                majorEnhance.setMoreBeforeMajorMinScore(moreBeforeMajor.getMajor_min_score());
                majorEnhance.setMoreBeforeMajorMinSection(moreBeforeMajor.getMajor_min_section());
            }

            res.add(majorEnhance);
        }
        return res;
    }
}
