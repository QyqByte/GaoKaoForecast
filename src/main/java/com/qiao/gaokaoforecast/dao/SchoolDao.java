package com.qiao.gaokaoforecast.dao;

import com.qiao.gaokaoforecast.domain.School;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QYQ
 */
@Mapper
@Repository
public interface SchoolDao {

    /**
     * @param school 添加的school对象
     * @return
     */
    int insertschool(School school);

    /**
     * 通过学校名查找学校
     * @param name 学校名
     * @return 查找到的学校的名称
     */
    School selectSchoolByName(String name);


    /**
     * 通过学校id查找学校

 * @param school_id 学校id
     * @version 0.1.0
     * @return School
     * @author QYQ
     * @date 2021/7/28 15:06
     * @since 0.1.0
     */
    School selectSchoolBySchoolId(int school_id);

    /**
     * 查找所有的学校

     * @version 0.1.0
     * @return java.util.List<com.qiao.gaokaoforecast.domain.School>
     * @author QYQ
     * @date 2021/7/28 15:08
     * @since 0.1.0
     */
    List<School> selectAllSchools();

    /**
     * 通过school_id删除指定的学校
     id：表示学校所对应的school_id
     * @version 0.1.0
     * @return 返回1表示删除成功
     * @author QYQ
     * @date 2021/7/28 16:06
     * @since 0.1.0
     */
    int deleteSchoolById(int schoolId);
}
