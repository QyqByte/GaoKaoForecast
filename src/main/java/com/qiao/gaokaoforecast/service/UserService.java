package com.qiao.gaokaoforecast.service;

import com.qiao.gaokaoforecast.domain.User;
import org.springframework.stereotype.Service;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-08-19 17:15
 * @since 0.1.0
 **/
public interface UserService {
    int addUser(User user);

    int deleteUserByUserName(String userName);

    User selectUserByUserName(String userName);

    String isObtainUser(String userName);
}
