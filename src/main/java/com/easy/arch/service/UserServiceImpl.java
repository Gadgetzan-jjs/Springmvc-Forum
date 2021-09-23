package com.easy.arch.service;

import com.easy.arch.annotation.Cache;
import com.easy.arch.dao.UserDao;
import com.easy.arch.entity.Message;
import com.easy.arch.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {
    @Autowired
    private UserDao userDao;

    public User findUserByUsernameAndPassword(String username,String password){
        return userDao.findUserByUsernameAndPassword(username,password);
    }
    public int registerUser(String username,String password){
       return userDao.registerUser(username,password);
    }



}
