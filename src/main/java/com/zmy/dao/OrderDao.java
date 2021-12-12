package com.zmy.dao;

import com.zmy.pojo.Order;
import com.zmy.pojo.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {

    /**
     * 查询订单列表通过userId
     * @param userId
     * @return
     */
    @Select("SELECT *FROM t_order WHERE userId=#{userId}")
    List<Order> queryOrderList(Integer userId);

    /**
     * 查询订单详情，通过orderId
     * @param orderId
     * @return
     */
    @Select("select * from t_order_item where orderId=#{orderId}")
    List<OrderItem> queryOrderDetailsById(String orderId);

    /**
     * 用户确认收货，并返回
     * @param orderId
     * @return
     */
    @Update("UPDATE t_order SET STATUS=2 where orderId=#{orderId}")
    Integer receive(String orderId);

    /**
     * 管理员查询所有订单
     * @return
     */
    @Select("select * from t_order")
    List<Order> queryAllOrders();

    @Insert("<script> " +
            "insert into t_order "+
            "(orderId,createTime,price,status,userId) " +
            "values " +
            "<foreach collection='orders' item='item' separator=',' > " +
            "(#{item.orderId},#{item.createTime},#{item.price},#{item.status},#{userId}) " +
            "</foreach> " +
            "</script> ")
    void batchCreateOrders(@Param("orders") List<Order> orders,@Param("userId") Integer userId);

    @Insert("<script> " +
            "insert into t_order_item " +
            "(name,price,totalPrice,count,orderId) " +
            "values " +
            "<foreach collection='orderItems' item='item' separator=',' > " +
            "(#{item.name},#{item.price},#{item.totalPrice},#{item.count},#{item.orderId}) " +
            "</foreach> " +
            "</script> ")
    void batchCreateOrderItems(@Param("orderItems") List<OrderItem> orderItems);

    @Update("UPDATE t_order SET STATUS=1 where orderId=#{orderId}")
    void sendOrder(String orderId);
}
