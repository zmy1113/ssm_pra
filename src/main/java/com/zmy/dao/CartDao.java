package com.zmy.dao;

import com.zmy.pojo.CartItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao {
    @Select("select * from t_cart where userId=#{userId}")
    List<CartItem> queryCartByUserId(Integer userId);

    @Select("select * from t_cart where userId=#{userId} and name=#{name}")
    String existCartItem(@Param("name")String name,@Param("userId")Integer userId);

    @Update("UPDATE t_cart SET COUNT=t_cart.`count`+#{count},total_price=t_cart.`total_price`+#{count}*price" +
            " WHERE userId=#{userId} and name=#{name}")
    void updateCount(@Param("count") Integer count,@Param("name")String name,@Param("userId")Integer userId);

    @Insert("INSERT INTO t_cart (name,COUNT,price,total_price,userId)" +
            " VALUES(#{cartItem.name},#{cartItem.count},#{cartItem.price},#{cartItem.totalPrice},#{userId})")
    void saveCartItem(@Param("cartItem")CartItem cartItem,@Param("userId")Integer userId);

    @Delete("delete from t_cart where userId=#{userId}")
    void clear(Integer userId);

    @Delete("delete from t_cart where id=#{cartId}")
    void deleteById(Integer cartId);

    @Update("UPDATE t_cart SET COUNT=#{count},total_Price=#{count}*price WHERE userId=#{userId} and name=#{name}")
    void updateCountByUpdate(@Param("count") Integer count,@Param("name")String name,@Param("userId")Integer userId);
}
