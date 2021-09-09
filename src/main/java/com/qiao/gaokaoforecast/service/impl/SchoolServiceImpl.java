package com.qiao.gaokaoforecast.service.impl;

import com.qiao.gaokaoforecast.dao.SchoolDao;
import com.qiao.gaokaoforecast.domain.School;
import com.qiao.gaokaoforecast.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-07-28 19:56
 * @since 0.1.0
 **/
@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolDao schoolDao;
    @Override
    public int addschool(School school) {
        return schoolDao.insertschool(school);
    }

    @Override
    public School selectSchoolById(int schoolid) {
        School res = schoolDao.selectSchoolBySchoolId(schoolid);
        return res;
    }

    @Override
    public School selectSchoolByName(String schoolName) {
        return schoolDao.selectSchoolByName(schoolName);
    }

    @Override
    public List<School> selectAllSchools() {

        return schoolDao.selectAllSchools();
    }

    @Override
    public int deleteSchoolById(int id) {

        return schoolDao.deleteSchoolById(id);
    }
}
