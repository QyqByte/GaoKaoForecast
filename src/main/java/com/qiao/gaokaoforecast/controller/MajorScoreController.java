package com.qiao.gaokaoforecast.controller;

import com.qiao.gaokaoforecast.domain.Major;
import com.qiao.gaokaoforecast.domain.MajorEnhance;
import com.qiao.gaokaoforecast.domain.School;
import com.qiao.gaokaoforecast.domain.ScoreBatch;
import com.qiao.gaokaoforecast.service.MajorService;
import com.qiao.gaokaoforecast.service.SchoolService;
import com.qiao.gaokaoforecast.service.ScoreBatchService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-08-04 17:07
 * @since 0.1.0
 **/
@Controller
public class MajorScoreController {
    @Autowired
    private SchoolService schoolService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private ScoreBatchService scoreBatchService;

    private Calendar calendar = Calendar.getInstance();
    private int currentyear = calendar.get(Calendar.YEAR);
    /**
     * 实现单纯的使用高考分数来进行推荐查找，要注意这里需要用到每年
     * 的高考分数线

 * @param score 表示前端传来的高考成绩
     * @version 0.1.0
     * @return
     * @author QYQ
     * @date 2021/8/4 17:09
     * @since 0.1.0
     */
    @RequestMapping("/searchByScore")
    public ModelAndView searchByScore(@RequestParam("score") int score) {
        ModelAndView model = new ModelAndView();

        // 先计算今年该分数所对应的分值差
        ScoreBatch scoreBatch = scoreBatchService.getbatchByYear(currentyear);
        int diffvalue = 0;
        int liFirst = scoreBatch.getBatchLiFirst();
        int liSecond = scoreBatch.getBatchLiSecond();
        byte batch = 1;
        if(score>=liFirst){
            diffvalue = score-liFirst;
            batch = 0;
        }else if(score>=liSecond){
            diffvalue = score-liSecond;
        }else {
            model.setViewName("error");
        }
        // 获取在该分值差范围内推荐的专业majors,这里默认采取的查找范围为目标分数的上下10分
        List<MajorEnhance> matchmajors = majorService.selectMajorByScore(diffvalue, 10, batch);

        int nums = matchmajors.size();
        // 存放所有的学校键值对
        Map<Integer,String> schoolMaps = new HashMap<>();
        // 存放该学校符合条件的专业
        Map<String,List<MajorEnhance>> majorMaps = new HashMap<>();
        List<MajorEnhance> li = new ArrayList<>();
        String lastschool = "";
        String nowschool = "";
        int key = 0;
        int major_year = 2020;

        // 遍历判断每个专业是属于什么学校的，以及将它们分类保存
        for (MajorEnhance major : matchmajors) {
            int schoolid = major.getNowmajor().getMajor_school_id();
            if(!schoolMaps.containsKey(schoolid)) {
                School school = schoolService.selectSchoolById(schoolid);
                String school_name = school.getSchool_name();

                nowschool = school_name;
                if(key==0){
                    lastschool = nowschool;
                    major_year = major.getNowmajor().getMajor_year();
                    key++;
                }

                schoolMaps.put(schoolid,school_name);
                if(!li.isEmpty()){
                    List<MajorEnhance> temp = new ArrayList<>(li);
                    majorMaps.put(lastschool,temp);
                    lastschool = nowschool;
                    li.clear();
                }
                li.add(major);
            }else {
                li.add(major);
            }
        }
        majorMaps.put(lastschool,li);
        Collection<String> allschool = schoolMaps.values();
        model.addObject("majorYear",major_year);
        model.addObject("score",score);
        model.addObject("allschools",allschool);
        model.addObject("majors",majorMaps);
        model.addObject("nums",nums);
        model.setViewName("searchByScoreResult");
        //model.setViewName("tables");
        return model;
    }

    /**
     * 同时使用高考成绩和高考名次来进行学校和专业的推荐

 * @param score 高考分数
     *    scoreRank 高考名次
     * @version 0.1.0
     * @return
     * @author QYQ
     * @date 2021/8/4 20:26
     * @since 0.1.0
     */
    @RequestMapping("/searchByRankAndScoreResult")
    public ModelAndView searchUseAll(@RequestParam("scoreRank") int scoreRank,@RequestParam("score") int score) {
        ModelAndView model = new ModelAndView();

        // 先得到使用高考名次得到的推荐专业
        List<MajorEnhance> rankmajors = majorService.selectMajorByRank(scoreRank, 10000);
        // 再得到使用高考分数得到的推荐专业
        // 先计算今年该分数所对应的分值差
        ScoreBatch scoreBatch = scoreBatchService.getbatchByYear(currentyear);
        int diffvalue = 0;
        int liFirst = scoreBatch.getBatchLiFirst();
        int liSecond = scoreBatch.getBatchLiSecond();
        byte batch = 1;
        if(score>=liFirst){
            diffvalue = score-liFirst;
            batch = 0;
        }else if(score>=liSecond){
            diffvalue = score-liSecond;
        }else {
            model.setViewName("error");
        }
        // 获取在该分值差范围内推荐的专业majors,这里默认采取的查找范围为目标分数的上下10分
        List<MajorEnhance> scoremajors = majorService.selectMajorByScore(diffvalue, 10, batch);

        // 筛选得到最后的结果
        rankmajors.retainAll(scoremajors);

        int nums = rankmajors.size();
        // 存放所有的学校键值对
        Map<Integer,String> schoolMaps = new HashMap<>();
        // 存放该学校符合条件的专业
        Map<String,List<MajorEnhance>> majorMaps = new HashMap<>();
        List<MajorEnhance> li = new ArrayList<>();
        String lastschool = "";
        String nowschool = "";
        int key = 0;
        int major_year = 2020;

        // 遍历判断每个专业是属于什么学校的，以及将它们分类保存
        for (MajorEnhance major : rankmajors) {
            int schoolid = major.getNowmajor().getMajor_school_id();
            if(!schoolMaps.containsKey(schoolid)) {
                School school = schoolService.selectSchoolById(schoolid);
                String school_name = school.getSchool_name();

                nowschool = school_name;
                if(key==0){
                    lastschool = nowschool;
                    major_year = major.getNowmajor().getMajor_year();
                    key++;
                }

                schoolMaps.put(schoolid,school_name);
                if(!li.isEmpty()){
                    List<MajorEnhance> temp = new ArrayList<>(li);
                    majorMaps.put(lastschool,temp);
                    lastschool = nowschool;
                    li.clear();
                }
                li.add(major);
            }else {
                li.add(major);
            }
        }
        majorMaps.put(lastschool,li);
        Collection<String> allschool = schoolMaps.values();
        model.addObject("majorYear",major_year);
        model.addObject("score",score);
        model.addObject("scorerank",scoreRank);
        model.addObject("allschools",allschool);
        model.addObject("majors",majorMaps);
        model.addObject("nums",nums);
        model.setViewName("searchByRankAndScoreResult");
        //model.setViewName("tables");
        return model;
    }
}
