package com.qiao.gaokaoforecast.dao;

import com.qiao.gaokaoforecast.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-08-19 17:03
 * @since 0.1.0
 **/
@Mapper
public interface UserDao {
    int addUser(User user);

    int deleteUserByUserName(String userName);

    User selectUserByUserName(String userName);

    String isObtainUser(String userName);
}
