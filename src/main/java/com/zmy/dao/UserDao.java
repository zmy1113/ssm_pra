package com.zmy.dao;

import com.zmy.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    /**
     * 通过用户名查找用户信息
     * @param username
     * @return
     */
    @Select("select * from t_user where username=#{username}")
    @ResultType(User.class)
    User selectUserByUsername(String username);


    @Insert("INSERT INTO t_user(username,PASSWORD,email) VALUES(#{user.username},#{user.password},#{user.email})")
    void saveUser(@Param("user") User user);
}
