package com.qiao.gaokaoforecast.controller;

import com.qiao.gaokaoforecast.domain.Major;
import com.qiao.gaokaoforecast.domain.MajorEnhance;
import com.qiao.gaokaoforecast.domain.School;
import com.qiao.gaokaoforecast.domain.User;
import com.qiao.gaokaoforecast.service.MajorService;
import com.qiao.gaokaoforecast.service.SchoolService;
import com.qiao.gaokaoforecast.service.ScoreBatchService;
import com.qiao.gaokaoforecast.service.UserService;
import com.qiao.gaokaoforecast.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-07-29 14:46
 * @since 0.1.0
 **/
@Controller
public class MainController {
    @Autowired
    private SchoolService schoolService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private ScoreBatchService scoreBatchService;

    @Autowired
    private UserService userService;

    /**
     * 主页

 * @version 0.1.0
     * @return
     * @author QYQ
     * @date 2021/7/29 14:48
     * @since 0.1.0
     */
    @RequestMapping("/main")
    public ModelAndView mains(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        String username = (String)request.getSession().getAttribute("username");
        modelAndView.addObject("username",username);
        modelAndView.setViewName("main.html");
        return modelAndView;
    }

    @RequestMapping("/registerTest")
    public String registerTest(String username, String password,HttpServletRequest request){
        User user = userService.selectUserByUserName(username);

        if(user==null || "".equals(password)){
            if(!"".equals(username)){

                return "UserNotExist.html";
            }
            return "index.html";
        }
        String user_password = user.getUser_password();
        if(!password.equals(user_password)){
            return "PassWordError.html";
        }
        request.getSession().setAttribute("username",user.getUser_name());
        request.getSession().setAttribute("success","success");
        return "redirect:main";
    }


    @RequestMapping("/")
    public String index(){
        return "index.html";
    }
    @RequestMapping("/reg")
    public String register(){
        return "registers.html";
    }
    @RequestMapping("/table")
    public String table1(){
        return "tables.html";
    }
//
//    @RequestMapping("/error")
//    public String error(){
//        return "error";
//    }

    @RequestMapping(value = "/searchByscoreRank")
    public ModelAndView search(@RequestParam("scoreRank") int scoreRank){
        // System.out.println(scoreRank);

        ModelAndView model = new ModelAndView();
        // 此处默认在给定的排名上下一万名，当然也可以通过前端页面参数进行修改
        List<MajorEnhance> majors = majorService.selectMajorByRank(scoreRank, 10000);

        // 所有符合条件的专业记录数
        int nums = majors.size();
        model.addObject("nums",nums);

        // 存放所有的学校键值对
        Map<Integer,String> schoolMaps = new HashMap<>();
        // 存放该学校符合条件的专业
        Map<String,List<MajorEnhance>> majorMaps = new HashMap<>();
        List<MajorEnhance> li = new ArrayList<>();
        String lastschool = "";
        String nowschool = "";
        int key = 0;
        int major_year = 2020;
        for (MajorEnhance major : majors) {
            int schoolid = major.getNowmajor().getMajor_school_id();
            if(!schoolMaps.containsKey(schoolid)){
                School school = schoolService.selectSchoolById(schoolid);
                String school_name = school.getSchool_name();

                // System.out.println(school_name);
                nowschool = school_name;
                if(key==0){
                    lastschool = school_name;
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
        Collection<String> schoolValues = schoolMaps.values();
        model.addObject("majorYear",major_year);
        model.addObject("scorerank",scoreRank);
        model.addObject("allSchools",schoolValues);
        model.addObject("majors",majorMaps);
        model.setViewName("searchByRankResult");
        //model.setViewName("tables");
        return model;
    }

    /*@RequestMapping("/searchJson")
    @ResponseBody
    public List<Object> searchJson(@RequestParam("scoreRand") int scoreRank) {
        // 此处默认在给定的排名上下一万名，当然也可以通过前端页面参数进行修改
        List<Major> majors = majorService.selectMajorByRank(scoreRank, 10000);

        // 所有符合条件的专业记录数
        int nums = majors.size();

        // 存放所有的学校键值对
        Map<Integer,Object> schoolMaps = new HashMap<>();
        // 存放该学校符合条件的专业
        Map<String,List<Major>> majorMaps = new HashMap<>();

        List<School> schools = new ArrayList<>();

        List<Major> li = new ArrayList<>();

        String lastschool = "";
        String nowschool = "";
        int key = 0;
        for (Major major : majors) {
            int schoolid = major.getMajor_school_id();
            if(!schoolMaps.containsKey(schoolid)){
                School school = schoolService.selectSchoolById(schoolid);
                String school_name = school.getSchool_name();

                // System.out.println(school_name);
                nowschool = school_name;
                if(key==0){
                    lastschool = school_name;
                    key++;
                }

                schoolMaps.put(schoolid,school_name);

                schools.add(school);

                if(!li.isEmpty()){
                    List<Major> temp = new ArrayList<>(li);
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
        System.out.println("schools.size"+schools.size());
        System.out.println("majors.size"+majorMaps.size());

        List<Object> list = new ArrayList<>();
        list.add(schools);
        list.add(majorMaps.values());
        return list;
    }
*/
    /*@RequestMapping("/searchByScore")
    public ModelAndView searchByScore(int score){
        ModelAndView model  =  new ModelAndView();
        List<ScoreBatch> everyYearBatch = scoreBatchService.getEveryYearBatch();
        List<ScoreBatch> e2 = new ArrayList<>();
        e2.retainAll(everyYearBatch);
    }*/
        @RequestMapping("/test")
        public String hello(){
            return "test.html";
        }
}
