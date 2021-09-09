package com.qiao.gaokaoforecast.service;

import com.qiao.gaokaoforecast.domain.School;

import java.util.List;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-07-28 19:52
 * @since 0.1.0
 **/
public interface SchoolService {

    int addschool(School school);

    School selectSchoolById(int schoolid);

    School selectSchoolByName(String schoolName);

    List<School> selectAllSchools();

    int deleteSchoolById(int id);
}
