package com.qiao.gaokaoforecast.dao;

import com.qiao.gaokaoforecast.domain.Major;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-07-28 15:10
 * @since 0.1.0
 **/
@Repository
@Mapper
public interface MajorDao {

    /**
     * 插入学科
     Major: 学科实体对象
     * @version 0.1.0
     * @return 返回1.则代表插入成功
     * @author QYQ
     * @date 2021/7/28 15:11
     * @since 0.1.0
     */
    int insertMajor(Major major);

    /**
     * 通过学校id查找所有的学科
     school_id: 学校id
     * @version 0.1.0
     * @return 所有的该学校的学科
     * @author QYQ
     * @date 2021/7/28 15:13
     * @since 0.1.0
     */
    List<Major> selectAllMajorBySchoolId(int schoolid);
    
    /**
     * 通过分数排名来查找所有满足条件的专业，后续在service层再将该专业与所对应的学校对应起来
     scorerank: 分数省排名，获取上下各一万名
     * @version 0.1.0
     * @return List<Major> 所有满足条件的专业
     * @author QYQ
     * @date 2021/7/28 21:23
     * @since 0.1.0
     */
    List<Major> selectAllMajorByScoreRank(int score_rank,int scope,int major_year);

    List<Major> selectMajorByScore(int score,int scope,int major_year);

}
