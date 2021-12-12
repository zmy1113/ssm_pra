package com.zmy.service;


import com.zmy.pojo.User;

public interface UserService {
    /**
     * 查找用户
     * @param username
     * @return
     */
    User selectUserByUsername(String username);

    boolean existUsername(String username);

    void saveUser(User user);
}
