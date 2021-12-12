package com.zmy.service.impl;

import com.zmy.dao.UserDao;
import com.zmy.pojo.User;
import com.zmy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    @Qualifier(value = ("userDao"))
    private UserDao userDao;

    @Override
    public User selectUserByUsername(String username) {
        User user = userDao.selectUserByUsername(username);
        return user;
    }

    /**
     * 根据用户名查找是否存在该用户名
     * @param username
     * @return 若存在则返回true，不存在返回false
     */
    @Override
    public boolean existUsername(String username){
       if(userDao.selectUserByUsername(username)==null){
           return false;
       }else {
           return true;
       }
    }
@Override
    public void saveUser(User user){
        userDao.saveUser(user);
    }
}
