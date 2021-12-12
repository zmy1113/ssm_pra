package com.zmy.service;

import com.zmy.pojo.CartItem;
import com.zmy.pojo.Order;
import com.zmy.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    List<Order> queryOrderList(Integer userId);

    List<OrderItem> queryOrderDetailsById(String orderId);

    Integer receive(String status);

    List<Order> queryAllOrders();

    void createOrder(List<CartItem> cartItems, Integer id);

    void sendOrder(String orderId);
}
