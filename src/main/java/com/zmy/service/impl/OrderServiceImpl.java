package com.zmy.service.impl;

import com.zmy.dao.OrderDao;
import com.zmy.pojo.CartItem;
import com.zmy.pojo.Order;
import com.zmy.pojo.OrderItem;
import com.zmy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> queryOrderList(Integer userId) {
       return orderDao.queryOrderList(userId);
    }

    @Override
    public List<OrderItem> queryOrderDetailsById(String orderId) {
       return orderDao.queryOrderDetailsById(orderId);
    }


    @Override
    public Integer receive(String status){
        return orderDao.receive(status);
    }

    @Override
    public List<Order> queryAllOrders() {
        return orderDao.queryAllOrders();
    }

    @Override
    public void createOrder(List<CartItem> cartItems, Integer userId) {
        List<Order> orders=new ArrayList<>();
        List<OrderItem> orderItems=new ArrayList<>();


        for (CartItem cartItem : cartItems) {
            OrderItem orderItem=new OrderItem();
            //获取订单号======》唯一性
            String orderId=System.currentTimeMillis()+""+userId;
            orders.add(new Order(orderId,new Date(),cartItem.getPrice(),0,userId));

            orderItem.setOrderId(orderId);
            orderItem.setName(cartItem.getName());
            orderItem.setCount(cartItem.getCount());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            try {
                orderItems.add(orderItem);
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        orderDao.batchCreateOrders(orders,userId);
        orderDao.batchCreateOrderItems(orderItems);
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.sendOrder(orderId);
    }

}
