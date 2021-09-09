package com.qiao.gaokaoforecast.service.impl;

import com.qiao.gaokaoforecast.dao.UserDao;
import com.qiao.gaokaoforecast.domain.User;
import com.qiao.gaokaoforecast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-08-19 17:16
 * @since 0.1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int deleteUserByUserName(String userName) {

        return userDao.deleteUserByUserName(userName);
    }

    @Override
    public User selectUserByUserName(String userName) {

        return userDao.selectUserByUserName(userName);
    }

    @Override
    public String isObtainUser(String userName) {
        return userDao.isObtainUser(userName);
    }
}
